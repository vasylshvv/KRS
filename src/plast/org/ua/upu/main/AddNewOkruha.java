package plast.org.ua.upu.main;

import java.util.ArrayList;
import java.util.List;

import plast.org.ua.upu.insert.InsertOkruha;

public class AddNewOkruha {
	public static void main(String[] args) {
		List<String> listokruh = new ArrayList<String>();
		listokruh.add("Вінницька");
		listokruh.add("Волинська");
		listokruh.add("Дніпропетровська");
		listokruh.add("Донецька");
		listokruh.add("Житомирська");
		listokruh.add("Закарпатська");
		listokruh.add("Запорізька");
		listokruh.add("Івано-Франківська");
		listokruh.add("Київська");
		listokruh.add("Кіровоградська");
		listokruh.add("Луганська");
		listokruh.add("Львівська");
		listokruh.add("Миколаївська");
		listokruh.add("Одеська");
		listokruh.add("Полтавська");
		listokruh.add("Рівненська");
		listokruh.add("Сумська");
		listokruh.add("Тернопільська");
		listokruh.add("Харківська");
		listokruh.add("Херсонська");
		listokruh.add("Хмельницька");
		listokruh.add("Черкаська");
		listokruh.add("Чернівецька");
		listokruh.add("Чернігівська");	
		listokruh.add("м.Київ");
		listokruh.add("м.Севастополь");
		
		
		
		
		for (int i=0; i<listokruh.size(); i++) {
			InsertOkruha okruha = new InsertOkruha();
			okruha.insert(listokruh.get(i));
		}
		
		
	}
}
