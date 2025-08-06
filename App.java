import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> persons = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        // Ввод покупателей
        System.out.println("Введите покупателей в формате: Имя=СуммаДенег");
        System.out.println("Разделяйте покупателей точкой с запятой (;)");
        System.out.println("Например: Петр=100;Мария=50");

        String personsInput = scanner.nextLine();
        String[] personsData = personsInput.split(";");

        for (String personData : personsData) {
            try {
                String[] parts = personData.split("=");
                if (parts.length != 2) {
                    System.out.println("Неверный формат ввода для покупателя: " + personData);
                    continue;
                }
                String name = parts[0].trim();
                double money = Double.parseDouble(parts[1].trim());
                Person person = new Person(name, money);
                persons.add(person);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            } catch (Exception e) {
                System.out.println("Ошибка при создании покупателя: " + personData);
                return;
            }
        }

        // Ввод продуктов
        System.out.println("\nВведите продукты в формате: Название=Цена");
        System.out.println("Разделяйте продукты точкой с запятой (;)");
        System.out.println("Например: Хлеб=10;Молоко=25");

        String productsInput = scanner.nextLine();
        String[] productsData = productsInput.split(";");

        for (String productData : productsData) {
            try {
                String[] parts = productData.split("=");
                if (parts.length != 2) {
                    System.out.println("Неверный формат ввода для продукта: " + productData);
                    continue;
                }
                String name = parts[0].trim();
                double cost = Double.parseDouble(parts[1].trim());
                Product product = new Product(name, cost);
                products.add(product);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            } catch (Exception e) {
                System.out.println("Ошибка при создании продукта: " + productData);
                return;
            }
        }

        // Процесс покупок
        System.out.println("\nПроцесс покупок:");
        System.out.println("Вводите: ИмяПокупателя-НазваниеПродукта");
        System.out.println("Для завершения введите: END");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("END")) {
                break;
            }

            String[] parts = input.split("-");
            if (parts.length != 2) {
                System.out.println("Неверный формат. Используйте: ИмяПокупателя-НазваниеПродукта");
                continue;
            }

            String personName = parts[0].trim();
            String productName = parts[1].trim();

            // Поиск покупателя
            Person buyer = null;
            for (Person person : persons) {
                if (person.getName().equals(personName)) {
                    buyer = person;
                    break;
                }
            }

            if (buyer == null) {
                System.out.println("Покупатель " + personName + " не найден");
                continue;
            }

            // Поиск продукта
            Product productToBuy = null;
            for (Product product : products) {
                if (product.getName().equals(productName)) {
                    productToBuy = product;
                    break;
                }
            }

            if (productToBuy == null) {
                System.out.println("Продукт " + productName + " не найден");
                continue;
            }

            // Попытка покупки
            if (buyer.buyProduct(productToBuy)) {
                System.out.println(buyer.getName() + " купил " + productToBuy.getName());
            } else {
                System.out.println(buyer.getName() + " не может позволить себе " + productToBuy.getName());
            }
        }

        // Вывод результатов
        System.out.println("\nРезультаты покупок:");
        for (Person person : persons) {
            System.out.println(person.toString());
        }

        scanner.close();
    }
}