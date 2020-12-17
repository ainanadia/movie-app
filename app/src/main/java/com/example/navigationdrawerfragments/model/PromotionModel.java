package com.example.navigationdrawerfragments.model;

public class PromotionModel  {

    int promoId;
    String promoImage;
    String promoTitle;
    String promoDescription;

    public PromotionModel(int promoId, String promoImage, String promoTitle, String promoDescription) {
        this.promoId = promoId;
        this.promoImage = promoImage;
        this.promoTitle = promoTitle;
        this.promoDescription = promoDescription;
    }

    public int getPromoId() {
        return promoId;
    }

    public void setPromoId(int promoId) {
        this.promoId = promoId;
    }

    public String getPromoImage() {
        return promoImage;
    }

    public void setPromoImage(String promoImage) {
        this.promoImage = promoImage;
    }

    public String getPromoTitle() {
        return promoTitle;
    }

    public void setPromoTitle(String promoTitle) {
        this.promoTitle = promoTitle;
    }

    public String getPromoDescription() {
        return promoDescription;
    }

    public void setPromoDescription(String promoDescription) {
        this.promoDescription = promoDescription;
    }
}
