C. Customize the HTML user interface for your customer’s application.

line 14, changed title to My PC Shop
line 19, changed to My PC Shop
line 22, changed H2 to Parts
line 58, Changed table H2 to Pre-Built PC's
____________________________________________________________________________________________________________________________________________________________________________
D. Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.
File name about.html in templates.

Lines 1-13
creates the view template file to be shown when a user clicks on the About Us button on the Mainscreen. Gives a little paragraph with some background on the company.

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>About</title>
</head>
<body>
<h1>About Us</h1>
<p>Brandon's PC Shop is a simple web application that allows users to add, update and delete pre-built PC's and PC Parts.
</p>
<a href="http://localhost:8080">Back to the Mainscreen</a>
</body>
</html>


Lines 1-15
Change description: creates a Controller for the About page that routes the url "/about" to the about.html view template file.

File name AboutController.java in a controllers section.

package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutController {

    //creates endpoint to display About page
    @GetMapping
    public String showAbout() {
        return "about";
    }
}

__________________________________________
E. Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.

File name BootStrapData
added code to the public BootStrapData class to fill in part repository

line 25-37 - insertion of a new repository for inhouse parts

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

lines 39-103 - added 5 new parts to the repository:

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

line 106-125

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
______________________________________________________________________________________________________
F. Add a “Buy Now” button to your product list.

filename: mainscreen.html

line 88, added this line to create Buy Now button next to Product Add/Delete interface
<a th:href="@{/buyNow(productID=${tempProduct.id})}" class="btn btn-primary btn-sm mb-3">Buy Now</a>

New files were created
succesfulbuynow.html creates purchase confirmation page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Confirmation Buy Now</title>
</head>
<body>
<h1>Brandon's PC Shop</h1>
<p>Congratulations! Your purchase was successful.</p>
<a href="http://localhost:8080">Back to the Mainscreen</a>
</body>
</html>

unsuccessfulbuynow.html creates purchase unsuccessful page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Unsuccessful Buy Now</title>
</head>
<body>
<h1>Brandon's PC Shop</h1>
<p>You did not successfully buy this product because its inventory is 0.</p>
<a href="http://localhost:8080">Back to the Mainscreen</a>
</body>
</html>

AddProductController.java line 176-190 creates method for decrementing product's inventory by 1 from the database and validating it

    @GetMapping("/buyNow")
    public String buyNow(@RequestParam("productID") int theId, Model theModel) {
        ProductService productService = context.getBean(ProductServiceImpl.class);
        Product product = productService.findById(theId);

        if (product.getInv() > 0) {
            product.setInv(product.getInv() - 1);
            productService.save(product);
            return "successfulbuynow";
        } else {
            return "unsuccessfulbuynow";
        }
    }
}

______________________________________________________________________________________________
G. Modify the parts to track maximum and minimum inventory by doing the following: Add additional fields to the part entity for maximum and minimum inventory. Modify the sample inventory to include the maximum and minimum fields. Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values. Rename the file the persistent storage is saved to. Modify the code to enforce that the inventory is between or at the minimum and maximum value.

Modified filename Part.java

lines 31-34 inserted 2 new variables (minInv, maxInv) using @Min annotation for minInv and maxInv

@Min(value = 0, message = "Max inventory value must be positive")
int maxInv;
@Min(value = 0, message = "Min inventory value must be positive")
int minInv;

50-54 added a default minimum inventory and max inventory values

public Part(long id, String name, double price, int inv) {
this.id = id;
this.name = name;
this.price = price;
this.inv = inv;

lines 89-101 created 4 new getter and setter methods to get and set both minInv and maxInv

public int getMaxInv() {
return maxInv;
}

    public void setMaxInv(int maxInv) {
        this.maxInv = maxInv;
    }

    public int getMinInv() {
        return minInv;
    }

    public void setMinInv(int minInv) {
        this.minInv = minInv;
    }

filename mainscreen.html

line 39-40  added table headers for Minimum Inventory and Maximum Inventory to display these in the table
<th>Min Inventory</th>
<th>Max Inventory</th>

line 85-86 added table rows (td) for min inv and max inv
<td th:text="${tempPart.minInv}">1</td>
<td th:text="${tempPart.maxInv}">1</td>

filename: bootstrap.java
added to each of the parts
setMinInv(1);
setMaxInv(10)

filename: InHousePartForm.html

line 26-39 added to this web form to enable user to enter min & max inventory limits
    <p>Part Min Inventory<input type="text" path="minInv" th:field="*{minInv}" placeholder="Min Inventory" class="form-control mb-4 col-4"/></p>
    <p th:if="${#fields.hasErrors('minInv')}" th:errors="*{minInv}">Min Inventory Error</p>

    <p>Part Max Inventory<input type="text" path="maxInv" th:field="*{maxInv}" placeholder="Max Inventory" class="form-control mb-4 col-4"/></p>
    <p th:if="${#fields.hasErrors('maxInv')}" th:errors="*{maxInv}">Max Inventory Error</p>

    <div th:if="${#fields.hasAnyErrors()}">
        <ul>
            <li th:each="err : ${#fields.allErrors()}" th:text="${err}">

            </li>
        </ul>

    </div>

Added filename Inventory Validator

line 3-49 ensures that no inventory value can fall below or rise above the set minimum/maximum values.

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
*
*
*
*
*/
public class InventoryValidator implements ConstraintValidator<ValidInventory, Part> {
@Autowired
private ApplicationContext context;
public static  ApplicationContext myContext;

    @Override
    public void initialize(ValidInventory constraintAnnotation) {
        //ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext constraintValidatorContext) {

        //create code to make sure that inventory is between min and max value
        if(part.getInv() > part.getMaxInv())
        {
            //display error messege
            constraintValidatorContext.buildConstraintViolationWithTemplate("solution: Fix your inventory, it is greater than the max inventory").addConstraintViolation();
            return false;
        }

        if(part.getInv() < part.getMinInv())
        {
            //display error messege
            constraintValidatorContext.buildConstraintViolationWithTemplate("solution: Fix your inventory, it is less than the min inventory").addConstraintViolation();
            return false;
        }

        return true;
    }
}
_________________________________________________________________________________
H. Add validation between or at the maximum and minimum fields.
Display error messages for low inventory when adding and updating PARTS if the inventory is less than the minimum number of parts.
Display error messages for low inventory when adding and updating PRODUCTS lowers the part inventory below the minimum.


line 1-44 modify EnufPartsValidator

package com.example.demo.validators;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
*
*
*
*
*/
public class EnufPartsValidator implements ConstraintValidator<ValidEnufParts, Product> {
@Autowired
private ApplicationContext context;
public static  ApplicationContext myContext;
@Override
public void initialize(ValidEnufParts constraintAnnotation) {
ConstraintValidator.super.initialize(constraintAnnotation);
}

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext constraintValidatorContext) {
        if(context==null) return true;
        if(context!=null)myContext=context;
        ProductService repo = myContext.getBean(ProductServiceImpl.class);
        if (product.getId() != 0) {
            Product myProduct = repo.findById((int) product.getId());
            for (Part p : myProduct.getParts()) {
                if (p.getInv()<(product.getInv()-myProduct.getInv()))return false;
            }
            return true;
        }
        else{
                return true;
            }
    }
}

Modify ValidEnufParts 1-24

package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
*
*
*
*
*/
@Constraint(validatedBy = {EnufPartsValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEnufParts {
String message() default "There aren't enough parts in inventory!";
Class<?> [] groups() default {};
Class<? extends Payload> [] payload() default {};

}

Modify product.java to apply validator to product classes line 20 + import statement
import com.example.demo.validators.ValidEnufParts;
...
@ValidEnufParts

_________________________________________________________________________________

I. Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.

filename: PartTest

added code lines 160 - 185 to create 3 unit test for the getter and setter methods for Minimum & Maximum inventory within the Part class.

@Test
void getMinInv() {
int mininv = 10;
partIn.setMinInv(mininv);
assertEquals(mininv, partIn.getMinInv());
partOut.setMinInv(mininv);
assertEquals(mininv, partOut.getMinInv());
}

    @Test
    void setMaxInv() {
        int maxinv = 10;
        partIn.setMaxInv(maxinv);
        assertEquals(maxinv, partIn.getMaxInv());
        partOut.setMaxInv(maxinv);
        assertEquals(maxinv, partOut.getMaxInv());
    }

    @Test
    void setMinInv() {
        int mininv = 10;
        partIn.setMinInv(mininv);
        assertEquals(mininv, partIn.getMinInv());
        partOut.setMinInv(mininv);
        assertEquals(mininv, partOut.getMinInv());
    }

___________________________________________________________________________________
J. Remove the class files for any unused validators in order to clean your code.

DELETED unused validator:

filename: DeletePartValidator.java

filename: ValidDeletePart.java
