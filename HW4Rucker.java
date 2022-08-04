// Homework 4: Cash Register Program
// Course: CIS357
// Due date: 8/2/2022
// Name: Christopher Rucker
// Instructor: Il-Hyung Cho
// Program description: This program implements the POS system.
/* Program features: Change the item code type to String: Y
Provide the input in CSV format. Ask the user to enter the input file name: Y
Implement exception handling for
    File input: P
    Checking wrong input data type: Y
    Checking invalid data value: P
    Tendered amount less than the total amount: Y
Use HashMap for the items data: Y
Adding item data: Y
Deleting item data: Y
Modifying item data: N */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HW4Rucker {
    public static void main(String[]args) throws FileNotFoundException {
        ProductCatalog pc = new ProductCatalog();
        Register register = new Register(pc);


/** Initializing all of the variables im going to use */
        Scanner input = new Scanner(System.in);
        String answer = "";
        String answer2 = "";
        String productCode = "";
        int quantity = 0;
        float amountPaid = 0f;
        float subTotal = 0f;
        float totalSale = 0f;
        float salesTax = 0.06f;
        ArrayList<String> storedItems = new ArrayList<>();

        System.out.println("Welcome to POST system!\n");
        System.out.print("input file: ");
        String inputFile = input.next();
        java.io.File file = new java.io.File(inputFile);

        // Create a file
        java.io.PrintWriter output = new java.io.PrintWriter(file);

        System.out.print("Beginning new sale(Y/N) ");
        answer = input.next();

        System.out.println("----------------");
        /**sets the match variable to false until user inputs a proper product code*/
        boolean match = false;

        while (!answer.equalsIgnoreCase("N")) {
            System.out.print("Enter product code:");
            productCode = input.next();
            register.makeNewSale();

            /** prompts the user to enter the product codes so they can purchase items that they want */
            while (!productCode.trim().equals("-1")) {

                    /**checks to see if the product code that the user entered exists in the HashMap,
                     * if the user enters a proper product code then the match variable will be switched to false,
                     * only if the item is found in the HasMap*/
                   try {
                       if (pc.getSpecification(productCode).getID().trim().equals(productCode.trim())) {
                           match = true;
                           System.out.println("item name: " + pc.getSpecification(productCode).getDescription());
                           /** This try catch block throws an error if the user enters input that is not a valid data type for quantity */
                           try {
                               System.out.print("Enter Quantity: ");
                               quantity = input.nextInt();
                               SalesLineItem sli = new SalesLineItem(pc.getSpecification(productCode),quantity);
                               System.out.println("Item total: $" + sli.getSubtotal());
                               subTotal = (float) (sli.getSubtotal()+subTotal);

                               /**adds the bought item to a separate Arraylist that keeps tracks of purchased items**/
                               storedItems.add(" \t" + quantity + " \t" + pc.getSpecification(productCode).getDescription() + " $" + pc.getSpecification(productCode).getPrice());





                               /** informs the user that they must enter a valid data type for quantity
                                * @throws exception if data type is invalid.**/
                           } catch (InputMismatchException e) {
                               System.out.println("!! invalid data type");
                               System.out.println("Enter Quantity: ");

                           }
                           register.enterItem(pc.getSpecification(productCode).getID(),quantity);

                       }

                   }catch (NullPointerException e){

                   }

                /** This checks if the user wants to view the item list by calling the view products method that holds the items, and printing the information for them all */
                if (productCode.trim().equals("0000")) {
                    match = true;

                    System.out.printf("item code\t" + "item name\t" + "unit price\n");
                    pc.viewProducts();
                    System.out.print("Enter product code:");
                    productCode = input.next();

                }





                /** if match is never changed to true, then the user didnt enter a valid product code, and this notifies the user to enter a valid one */
                if (match == false) {
                    System.out.println("! ! invalid product code");

                }

                /** continues to ask use for product code until -1 is entered,
                 * switches the match variable back to false after each iteration of the loop to check for valid input */
                System.out.print("\nEnter product code:");
                productCode = input.next();
                //sets match back to false
                match = false;
            }

            System.out.println("---------------------");
            System.out.println("Item list:");


            for (int i = 0; i < storedItems.size(); i++) {
                System.out.println(storedItems.get(i));
            }
            System.out.println("Subtotal: \t$" + subTotal);
            //computing the total
            double totalAmountWithTax = computePrice(subTotal, salesTax);
            System.out.println("Total with tax (6%) \t\t$" + computePrice(subTotal,salesTax));

            System.out.print("tendered amount: \t\t$");
            amountPaid = (float) input.nextDouble();
            Payment payment = new Payment(amountPaid);

            /** This while loop constantly prompts the user until they enter an amount that is more than the total */
            while ((float)totalAmountWithTax> payment.getAmount()) {
                System.out.println("!! not enough. Enter again:");
                System.out.print("Tendered amount: \t\t$");

                /** Prompts user to input a value higher than the current */
                amountPaid = (float) input.nextDouble();
                payment.setAmount(amountPaid);

            }

            double change = payment.getAmount() - totalAmountWithTax;
            System.out.println("Change: \t\t$" + Math.round(change * 100.0) / 100.0);
            totalSale = (float) (totalSale + totalAmountWithTax);
            System.out.println("-------------------------------------\n");

            System.out.print("Beginning new sale(Y/N): ");
            answer = input.next();
            System.out.println("---------------------------------");

            /** restarts array list of sold items so the user can begin a new sale and resets the subtotal variable back to 0 */
            storedItems.removeAll(storedItems);
            subTotal = 0;

        }
        System.out.println("The total sale for the day is: " + totalSale);
        /** After the sale ends, this prompts the user to see if they want to make any updates */

        System.out.println("Do you want to update the item data? (A/D/M/Q): ");
        answer2 = input.next();

        /** if the user enters something other than Q, then they'll be able to make as many updates to the Hash Map */
        while (!answer2.equalsIgnoreCase("Q")) {
            /** this checks if the User enters A, which calls a method that adds the item to the Hash Map.  */
            if (answer2.equalsIgnoreCase("A")) {
                /**This prompts them to enter the item code,name, and price of the new item**/
                System.out.println("item code: ");
                String newItemCode = "";
                newItemCode = input.next();
                System.out.print("\nitem name: ");
                String newItemName = "";
                newItemName += input.next();
                System.out.println(newItemName);
                System.out.println("\nitem price: ");
                float newItemPrice = 0.0f;
                newItemPrice = (float) input.nextDouble();

                /** adds the new item to the Hash Map by calling the method**/
                pc.addProduct(newItemCode,newItemName,newItemPrice);

                System.out.print("Item add successful!");
                /** this checks if the User enters D, which deletes an item from the Hash Map.  */

            } else if (answer2.equalsIgnoreCase("D")) {
                /**This prompts them to enter the item code of the item they want to delete**/

                System.out.println("item code: ");
                String delItem = input.next();
                /**Removes the item by its itemCode which is the key in the HashMap.**/

                pc.removeProd(delItem);
                System.out.println("Item delete sucessful");
                /** this checks if the User enters the letter M, which modifies an item in the hashMap  */
            } else if (answer2.equalsIgnoreCase("M")) {
                /**This prompts them to enter the item code of the item they want to modify**/
                System.out.println("item code: ");
                String modItemCode = input.next();

                System.out.println("item name: ");
                String modItemName = input.next();
                System.out.println("item price: ");
                float modItemPrice = (float) input.nextDouble();

                /**TThe method called that modifies the Hash Map**/

                pc.modifyItem(modItemCode,pc,pc.getSpecification(modItemCode),modItemPrice,modItemName);


                System.out.println("item modify sucessful!\n");
            }
            /**Asks user if they want to make additional updates to the items.**/
            System.out.println("Do you want to update the item data? (A/D/M/Q): ");
            answer2 = input.next();


        }
        /**prints the item list out at the end of the program**/
        pc.viewProducts();





    }
    /**This method calculates the price after taxes**/

    public static double computePrice(double price, double tax) {

        double salesTax = price * tax;
        double total = salesTax + price;
        return Math.round(total*100)/100.0;

    }
}
