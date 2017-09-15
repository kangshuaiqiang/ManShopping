package bwie.com.manshopping.GreenDao.GreenDao_Count;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 黑白 on 2017/9/14.
 */
@Entity
public class Count_Price {
    @Id(autoincrement = true)
    private Long id;
    private int count;
    private float price;
    public float getPrice() {
        return this.price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1799754715)
    public Count_Price(Long id, int count, float price) {
        this.id = id;
        this.count = count;
        this.price = price;
    }
    @Generated(hash = 2124352173)
    public Count_Price() {
    }


}
