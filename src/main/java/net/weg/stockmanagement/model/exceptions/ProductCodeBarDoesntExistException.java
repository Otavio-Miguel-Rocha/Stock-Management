package net.weg.stockmanagement.model.exceptions;

public class ProductCodeBarDoesntExistException extends Exception{
    public ProductCodeBarDoesntExistException(Long codeBar){
        super("The product with code bar " + codeBar + " doesn't exist!");
    }
}
