package plast.org.ua.upu.main;

import java.util.ArrayList;
import java.util.List;

import plast.org.ua.upu.insert.InsertOkruha;

public class AddNewOkruha {
	public static void main(String[] args) {
		List<String> listokruh = new ArrayList<String>();
		listokruh.add("³�������");
		listokruh.add("���������");
		listokruh.add("���������������");
		listokruh.add("��������");
		listokruh.add("�����������");
		listokruh.add("������������");
		listokruh.add("���������");
		listokruh.add("�����-����������");
		listokruh.add("�������");
		listokruh.add("ʳ������������");
		listokruh.add("���������");
		listokruh.add("��������");
		listokruh.add("�����������");
		listokruh.add("�������");
		listokruh.add("����������");
		listokruh.add("г��������");
		listokruh.add("�������");
		listokruh.add("������������");
		listokruh.add("���������");
		listokruh.add("����������");
		listokruh.add("�����������");
		listokruh.add("���������");
		listokruh.add("����������");
		listokruh.add("����������");	
		listokruh.add("�.���");
		listokruh.add("�.�����������");
		
		
		
		
		for (int i=0; i<listokruh.size(); i++) {
			InsertOkruha okruha = new InsertOkruha();
			okruha.insert(listokruh.get(i));
		}
		
		
	}
}
