package com.laba.service;

import com.laba.entity.Address;
import com.laba.entity.EducationalInstitution;
import com.laba.entity.Region;
import com.laba.repository.AddressRepository;
import com.laba.repository.EducationalInstitutionRepository;
import com.laba.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.util.Random;


/**
 * так как у нас база в памяти - то нужно её заполнять при первом запуске, поэтому
 * сформируем справочиники районов, адресов и учебных заведений 
 */
@Component
public class SetupDataLoader  implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    EducationalInstitutionRepository educationalInstitutionRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }
    public void init(){

    String[] regions ={"Центральный","Западный","Восточный","Северный"};
    String[] streets ={"Центральная","Молодежная","Школьная","Лесная",
        "Садовая",   "Советская", "Новая","Некрасова",
            "Набережная",    "Заречная",     "Зеленая","Горная",
            "Цветочная","Березовая","Мичурина","Чкалова",
            "Лермонтова","Пушкина","Толстого","Трудовая"};
    int i=0;
    int k =1;
    for(String name:regions){
        Region region = new Region(name);
        region =  regionRepository.save(region);
        Random random = new Random();
        for(int j=0;j<4;j++){
            String street = streets[i];
            int r = random.nextInt(5)+5;
            while (r>0){
                Address address = new Address(street, Integer.toString(r));
                address.setRegion(region);
                addressRepository.save(address);
                if (((i+1)%4==0 && r==1) || (((i+1)%4!=0) && random.nextInt(100)>93)){
                    address = new Address(street, Integer.toString((random.nextInt(10)+10)));
                    address.setRegion(region);
                    address = addressRepository.save(address);
                    EducationalInstitution educationalInstitution = new EducationalInstitution(Integer.toString(k),address);
                    educationalInstitutionRepository.save(educationalInstitution);
                    k++;
                }
                r--;
            }
            i++;
        }

    }



    }
}
