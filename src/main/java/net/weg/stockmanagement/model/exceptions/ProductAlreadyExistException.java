package net.weg.stockmanagement.model.exceptions;

public class ProductAlreadyExistException extends Exception{
    public ProductAlreadyExistException(){
        super("The product already exists in stock");
    }
}
