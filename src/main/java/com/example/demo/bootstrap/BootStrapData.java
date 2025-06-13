package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        OutsourcedPart p1 = new OutsourcedPart();
        p1.setCompanyName("Nvidia");
        p1.setName("RTX 4090");
        p1.setInv(5);
        p1.setMinInv(1);
        p1.setMaxInv(10);
        p1.setPrice(2500.0);
        outsourcedPartRepository.save(p1);

        OutsourcedPart p2 = new OutsourcedPart();
        p2.setCompanyName("AMD");
        p2.setName("RX 7900 XTX");
        p2.setInv(8);
        p2.setMinInv(1);
        p2.setMaxInv(10);
        p2.setPrice(999.0);
        outsourcedPartRepository.save(p2);

        OutsourcedPart p3 = new OutsourcedPart();
        p3.setCompanyName("RX");
        p3.setName("RX 9070");
        p3.setInv(3);
        p3.setMinInv(1);
        p3.setMaxInv(10);
        p3.setPrice(798.0);
        outsourcedPartRepository.save(p3);

        OutsourcedPart p4 = new OutsourcedPart();
        p4.setCompanyName("ASUS");
        p4.setName("RTX 5060 Ti");
        p4.setInv(1);
        p4.setMinInv(1);
        p4.setMaxInv(10);
        p4.setPrice(529.0);
        outsourcedPartRepository.save(p4);

        OutsourcedPart p5 = new OutsourcedPart();
        p5.setCompanyName("NVIDIA");
        p5.setName("RTX 4070");
        p5.setInv(9);
        p5.setMinInv(1);
        p5.setMaxInv(10);
        p5.setPrice(649.0);
        outsourcedPartRepository.save(p5);

        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("RTX 4090"))thePart=part;
            if(part.getName().equals("RX 7900 XTX"))thePart=part;
            if(part.getName().equals("RX 9070"))thePart=part;
            if(part.getName().equals("RTX 5060 Ti"))thePart=part;
            if(part.getName().equals("RTX 4070"))thePart=part;
        }

        System.out.println(thePart.getCompanyName());

        outsourcedParts = (List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }


        Product PC_1= new Product("PC 1",1000.0,15);
        Product PC_2= new Product("PC 2",1500.0,15);
        Product PC_3= new Product("PC 3",2000.0,15);
        Product PC_4= new Product("PC 4",3500.0,15);
        Product PC_5= new Product("PC 5",4000.0,15);
        productRepository.save(PC_1);
        productRepository.save(PC_2);
        productRepository.save(PC_3);
        productRepository.save(PC_4);
        productRepository.save(PC_5);


        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
