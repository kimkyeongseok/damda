package com.damda.ideaconcert.damda.admin.product.application;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.damda.ideaconcert.damda.common.application.EventService;
import com.damda.ideaconcert.damda.filemanager.application.FileManager;
import com.damda.ideaconcert.damda.product.application.ElementReadDto;
import com.damda.ideaconcert.damda.product.application.ProductReadDto;
import com.damda.ideaconcert.damda.product.application.ProductRequestDto;
import com.damda.ideaconcert.damda.product.domain.DeleteProductEvent;
import com.damda.ideaconcert.damda.product.domain.Element;
import com.damda.ideaconcert.damda.product.domain.ElementRepository;
import com.damda.ideaconcert.damda.product.domain.Product;
import com.damda.ideaconcert.damda.product.domain.ProductRepository;
import com.damda.ideaconcert.damda.utils.NameUtils;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {
    private final ProductRepository productRepository;
    private final ElementRepository elementRepository;
    private final FileManager fileManager;
    private final EventService eventService;

    @Override
    @Transactional
    public void create(ProductRequestDto productRequest) {
        String folderName = NameUtils.createUniqueName();
        Product product = new Product(
            productRequest.getName(), 
            productRequest.getCategory(),
            productRequest.getIsFree(), 
            folderName, 
            productRequest.getPrice()
        );
        product = productRepository.save(product);
        File folder = fileManager.getFolderFromFolderName(folderName);
        addElement(productRequest, product, folder);
    }

    @Override
    @Transactional
    public List<ProductReadDto> read(Pageable page) {
        List<Product> productList = productRepository.findAll();
        List<ProductReadDto> response = productList.stream().map(product -> {
            List<ElementReadDto> elementList = product.getElements().stream().map(element -> {
                return new ElementReadDto(
                    element.getId(), 
                    element.getName(), 
                    fileManager.createPath(product, element)
                );
            }).collect(Collectors.toList());
            return new ProductReadDto(
                product.getId(), 
                product.getName(), 
                product.getCategory(), 
                product.getIsFree(),
                getPreviewUrl(elementList), 
                product.getPrice(), 
                product.getUploadDate(), 
                elementList
            );
        }).collect(Collectors.toList());
        return response;
    }

    @Override
    @Transactional
    public void update(ProductRequestDto productRequest) {
        Product product = productRepository.getOne(productRequest.getId());
        // getPreview 함수명 고민해보시길
        String updateFolderName = product.getPreview();
        File updateFolder = fileManager.getFolderFromFolderName(updateFolderName);
        // // 폴더 하위에 파일이 존재하면 delete()가 작동하지 않음 => 하위 리스트를 받아 삭제 후 delete()
        Product updateProduct = new Product(
            productRequest.getName(), 
            productRequest.getCategory(),
            productRequest.getIsFree(), 
            updateFolderName, 
            productRequest.getPrice()
        );
        MultipartFile updateImg = productRequest.getImg().get(0);
        if (updateImg.getSize() != 0) {
            elementRepository.deleteByProductId(productRequest.getId());
            fileManager.deleteFileFromExistFolder(updateFolder);
        }
        product.update(updateProduct);
        addElement(productRequest, product, updateFolder);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(willDeleteProduct -> {
            String folderName = willDeleteProduct.getPreview();
            productRepository.deleteById(id);
            DeleteProductEvent deleteProductEvent = new DeleteProductEvent(folderName);
            eventService.publish(deleteProductEvent);
        });
    }

    private String getPreviewUrl(List<ElementReadDto> elementList) {
        return elementList.get(0).getImg();
    }

    private String getImageFileExtension(String imageName) {
        return imageName.substring(imageName.lastIndexOf(".") + 1);
    }

    private void addElement(ProductRequestDto productRequest, Product product, File folder) {
        for (MultipartFile file : productRequest.getImg()) {
            if (file.getSize() != 0) {
                String filePath = NameUtils.createUniqueName();
                String imageName = file.getOriginalFilename();
                String extension = getImageFileExtension(imageName);
                filePath += "." + extension;
                
                fileManager.uploadfile(file, folder, filePath);
                Element element = new Element(imageName, filePath);
                product.addElement(element);
            }
        }
    }
}
