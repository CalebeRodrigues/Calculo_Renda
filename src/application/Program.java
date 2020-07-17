package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		
		System.out.print("Entre com o nome do departamento: ");
		String nameDepartment = scanner.nextLine();
		System.out.println("Dados do trabalhador: ");
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		System.out.print("Nivel: ");
		String nivel = scanner.nextLine();
		System.out.print("Salario base: ");
		Double baseSalary = scanner.nextDouble();
		
		
		Worker worker = new Worker(nome, WorkerLevel.valueOf(nivel), baseSalary, new Department(nameDepartment));
		
		System.out.print("Quantos contratos com este trabalhador? ");
		int n = scanner.nextInt();
		
		for(int i = 0; i<n;i++) {
			System.out.println("Entre com o #"+ (i+1) +" contrato:");
			System.out.print("Data (DD/MM/YYYY): ");
			Date date = format.parse(scanner.next());
			System.out.print("Valor por hora: ");
			Double valuePerHour = scanner.nextDouble();
			System.out.print("Duração (horas): ");
			int hours = scanner.nextInt();
			
			HourContract contract = new HourContract(date, valuePerHour, hours);
			
			worker.addContract(contract);			
		}
		
		System.out.println();
		System.out.print("Digite o mês e o ano para calcular a renda: ");
		String monthAndYear = scanner.next();
		int month = Integer.parseInt(monthAndYear.substring(0, 2));
		int year = Integer.parseInt(monthAndYear.substring(3));
		
		System.out.println("Nome: " + worker.getName());
		System.out.println("Departamento: " + worker.getDepartment().getName());
		System.out.println("Renda para "+monthAndYear+": "+ String.format("%.2f", worker.income(year, month)));
		scanner.close();
	}

}
