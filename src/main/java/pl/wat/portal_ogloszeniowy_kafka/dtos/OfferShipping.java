package pl.wat.portal_ogloszeniowy_kafka.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OfferShipping {
    protected Long id;
    private String title;
    private float price;
    private String details;
    private byte[] photos;
    private String userLogin;
    private Date date;
    List<TagResponseDto> tags;
    int status;

    public OfferShipping(Long id, String title, float price, String details, byte[] photos, String userLogin, Date date,
                         List<TagResponseDto> tags) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.details = details;
        this.photos = photos;
        this.userLogin = userLogin;
        this.date = date;
        this.tags = tags;
        this.status = 0;
    }
}
