package application;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employee> list = new ArrayList<>();

        System.out.print("How many employees will be registered? ");
        int N = sc.nextInt();

        for (int i = 0; i < N; i++) {

            System.out.println("Emplyoee #" + (i + 1) + ": ");
            System.out.print("Id: ");
            Integer id = sc.nextInt();
            //Validação para não haver id repetido
            while (hasId(list, id)){
                System.out.print("Id already taken! Try again: ");
                id = sc.nextInt();
            }

            System.out.print("Name: ");
            sc.nextLine(); //Para limpar o buffer
            String name = sc.nextLine(); //O nextLine lê até a quebra de linha, usar qdo tem espaço entre as palavras
            System.out.print("Salary: ");
            Double salary = sc.nextDouble();

            Employee emp = new Employee(id, name, salary);

            list.add(emp);
        }

        System.out.println();
        System.out.print("Enter the employee id that will have salary increase: ");
        int id = sc.nextInt();

        //Procurando o id na lista
        //pega o primeiro da lista filtrada pelo id ou retorna null
        Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (emp == null) {
            System.out.print("This id doesn't exist!");
        } else {
            System.out.print("Enter the percentage: ");
            double percent = sc.nextDouble();
            emp.increaseSalary(percent);


            //Procurando usando a função position criada (mais verboso)
//        Integer pos = position(list, id);
//        if (pos == null){
//            System.out.print("This id doesn't exist!");
//        }
//        else {
//            System.out.print("Enter the percentage: ");
//            double percent = sc.nextDouble();
//            list.get(pos).increaseSalary(percent); //.get acessa o objeto com o índice passado de argumento
//        }

            //Imprimindo a lista de funcionários
            System.out.println();
            System.out.println("List of employees: ");
            for (Employee e : list) {
                System.out.println(e);

            }

            sc.close();
        }
    }

    //Verificando se o id existe
    public static boolean hasId(List<Employee> list, int id){
        Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return emp != null; //retorna verdadeiro se encontrar o id na list
    }

    //outra forma de verificar o id
    public static Integer position(List<Employee> list, int id){
        for (int i=0; i< list.size(); i++){
            if (list.get(i).getId() == id){
                return i;
            }
        }
        return null; //Usando a classe Integer, pode retornar null; se usar int o retorno deve ser -1
    }
}
