import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private final List<Product> bag;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.bag = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Имя не может быть короче 3 символов");
        }
        this.name = name;
    }

    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.money = money;
    }

    public boolean buyProduct(Product product) {
        if (money >= product.getCost()) {
            money = money - product.getCost();
            bag.add(product);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" - ");
        if (bag.isEmpty()) {
            sb.append("Ничего не куплено");
        } else {
            for (int i = 0; i < bag.size(); i++) {
                sb.append(bag.get(i).getName());
                if (i < bag.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Person person = (Person) obj;
        return Double.compare(person.money, money) == 0 &&
                name.equals(person.name) &&
                bag.equals(person.bag);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + Double.hashCode(money);
        result = 31 * result + bag.hashCode();
        return result;
    }
}