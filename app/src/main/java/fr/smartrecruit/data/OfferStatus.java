package fr.smartrecruit.data;

public class OfferStatus {
    private String message;
    private String color;
    private String favorite;

    public OfferStatus(String message, String color) {
        this.message = message;
        this.color = color;
    }

    public String getMessage() {
        return message;
    }

    public String getColor() {
        return color;
    }

    public  String getFavorite(){
        return favorite;
    }
}
