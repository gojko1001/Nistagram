package com.agent.webshop.service;

import com.agent.webshop.controller.dto.ImageByte;
import com.agent.webshop.controller.dto.ImageBytesDto;
import com.agent.webshop.controller.mapper.ItemMapper;
import com.agent.webshop.domain.Item;
import com.agent.webshop.repository.IItemRepository;
import com.agent.webshop.service.interfaces.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService implements IItemService {
    @Autowired
    private IItemRepository itemRepository;

    private static String uploadDir = "images";

    @Override
    public List<ImageBytesDto> getImagesFiles(List<Item> items) {
        List<ImageBytesDto> imageBytesDtos = new ArrayList<>();
        if (items != null) {
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("/src/main/resources/" + uploadDir + "/");
            for (Item item : items) {
                imageBytesDtos.add(imageFile(item, filePath));
            }
        }
        return imageBytesDtos;
    }

    @Override
    public ImageBytesDto imageFile(Item item, String filePath) {
        ImageBytesDto imageBytesDtos = ItemMapper.mapItemToImageBytesDto(item);
        File in = new File(filePath + item.getFileName());
        ImageByte imageByte = new ImageByte();
        try {
            imageByte.setImageByte(IOUtils.toByteArray(new FileInputStream(in)));
            imageByte.setImage(true);
            imageBytesDtos.getImageBytes().add(imageByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageBytesDtos;
    }

    @Override
    public Item create(Item item){
        return itemRepository.save(item);
    }

    @Override
    public List<ImageBytesDto> getItemsByUsername(String username){
        return getImagesFiles(itemRepository.findItemsByUsername(username));
    }

    @Override
    public ResponseEntity getItems(){
        return new ResponseEntity(getImagesFiles(itemRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public Item getItemById(Long id){
        return itemRepository.findItemById(id);
    }

    @Override
    public ResponseEntity updateItem(String username, Item newItem){
        Item item = itemRepository.findItemById(newItem.getId());
        if(!item.getUsername().equals(username)){
            return new ResponseEntity("You don't have permission to modify item.", HttpStatus.BAD_REQUEST);
        }
        if(item == null){
            return  new ResponseEntity("Item doesn't exist.", HttpStatus.BAD_REQUEST);
        }
        item.setName(newItem.getName());
        item.setDescription(newItem.getDescription());
        item.setQuantity(newItem.getQuantity());
        //item.setImage(newItem.getImage());
        item.setPrice(newItem.getPrice());
        item.setOnSale(newItem.isOnSale());
        itemRepository.save(item);
        return new ResponseEntity("Item has been updated.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity delete(String username, Long itemId){
        Item item = itemRepository.findItemById(itemId);
        if(item == null){
            return  new ResponseEntity("Item doesn't exist.", HttpStatus.BAD_REQUEST);
        }
        if(!item.getUsername().equals(username)){
            return new ResponseEntity("You don't have permission to delete item.", HttpStatus.BAD_REQUEST);
        }
        itemRepository.delete(item);
        // TODO: obrisi taj item u svim korpama
        return new ResponseEntity("Item has been deleted.", HttpStatus.OK);
    }

}
