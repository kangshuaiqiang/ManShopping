package bwie.com.manshopping.GreenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 黑白 on 2017/9/14.
 */
@Entity
public class Shooping {
    @Id(autoincrement = true)
    private Long id;
    //商品名字
    private String name;
    //Image图片链接
    private String image;
    //单价
    private String price;
    //数量
    private int count;
    //选中状态
    private boolean isNet;
    public boolean getIsNet() {
        return this.isNet;
    }
    public void setIsNet(boolean isNet) {
        this.isNet = isNet;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 457453746)
    public Shooping(Long id, String name, String image, String price, int count,
            boolean isNet) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.count = count;
        this.isNet = isNet;
    }
    @Generated(hash = 573040808)
    public Shooping() {
    }
}
