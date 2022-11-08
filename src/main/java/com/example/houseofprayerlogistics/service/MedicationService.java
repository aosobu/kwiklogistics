package com.example.houseofprayerlogistics.service;

import com.example.houseofprayerlogistics.constants.AppConstants;
import com.example.houseofprayerlogistics.dto.MedicationDTO;
import com.example.houseofprayerlogistics.entity.Medication;
import com.example.houseofprayerlogistics.repository.MedicationRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class MedicationService {

  private final MedicationRepository medicationRepository;
  private final Path root = Paths.get(AppConstants.FOLDER_PATH);

  @PostConstruct
  public void init(){
    try {
      if(!Files.exists(root)){
        Files.createDirectory(root);
      }
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Autowired
  public MedicationService(
      MedicationRepository medicationRepository) {
    this.medicationRepository = medicationRepository;
  }

  public boolean registerMedication(MedicationDTO medicationDTO) {
    Medication medication;
    boolean recordUpload;
    MultipartFile imageFile = Objects.requireNonNull(medicationDTO.getImage());

    String filePath = this.root.toString().concat("/").concat(
        Objects.requireNonNull(imageFile.getOriginalFilename()));

    Optional<Medication> medicationExists = medicationRepository.findMedicationByCode(medicationDTO.getCode());
    if(medicationExists.isPresent()){
      return true;
    }

    try{
      medication = Medication
          .builder()
          .name(medicationDTO.getName())
          .code(medicationDTO.getCode())
          .weight(medicationDTO.getWeight())
          .imagePath(filePath)
          .build();

      medication = medicationRepository.save(medication);
      recordUpload = true;
    }catch(Exception ex){
      AppConstants.MESSAGE = AppConstants.DATABASE_SAVE_MESSAGE;
      return false;
    }


    if(recordUpload){
      try{
        Files.copy(imageFile.getInputStream(), this.root.resolve(
            Objects.requireNonNull(imageFile.getOriginalFilename())));
      }catch(Exception ex) {
        medicationRepository.delete(medication);
        AppConstants.MESSAGE = AppConstants.DUPLICATE_IMAGE_MESSAGE;
        return false;
      }
    }

    return true;
  }

}
