package application;

import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Entre o caminho do arquivo: ");
        String path = sc.nextLine();


        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            List<Sale> list = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                String[] field = line.split(",");
                list.add(new Sale(Integer.parseInt(field[0]), Integer.parseInt(field[1]), field[2], Integer.parseInt(field[3]), Double.parseDouble(field[4])));
                line = br.readLine();
            }

            System.out.println();
            System.out.println("Cinco primeiras vendas de 2016 de maior preço médio: ");

            List<Sale> newList = list.stream()
                    .filter(x -> x.getYear() == 2016)
                    .sorted((s1, s2) -> s2.averagePrice().compareTo(s1.averagePrice()))
                    .limit(5)
                    .collect(Collectors.toList());

            for (Sale sale : newList) {
                System.out.println(sale);
            }


            System.out.println();
            double totalseller = list.stream()
                    .filter(x -> x.getMonth() == 1 || x.getMonth() == 7)
                    .filter(x -> x.getSeller().equals("Logan"))
                    .map(x -> x.getTotal())
                    .reduce(0.0, (x,y) -> x + y);

            System.out.println("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = " + String.format("%.2f", totalseller));


        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
