package cn.judgeTool.util;

import java.util.ArrayList;

import static java.lang.Math.*;

public class MathTool {

	public static double similarity(ArrayList va, ArrayList vb) {
		if (va.size() > vb.size()) {
			int temp = va.size() - vb.size();
			for (int i = 0; i < temp; i++) {
				vb.add(0);
			}
		} else if (va.size() < vb.size()) {
			int temp = vb.size() - va.size();
			for (int i = 0; i < temp; i++) {
				va.add(0);
			}
		}

		int size = va.size();
		double simVal;


		double num = 0;
		double den = 1;
		double powa_sum = 0;
		double powb_sum = 0;
		for (int i = 0; i < size; i++) {
			double a = Double.parseDouble(va.get(i).toString());
			double b = Double.parseDouble(vb.get(i).toString());

			num = num + a * b;
			powa_sum += pow(a, 2);
			powb_sum += pow(b, 2);
		}
		double sqrta = sqrt(powa_sum);
		double sqrtb = sqrt(powb_sum);
		den = sqrta * sqrtb;

		simVal = num / den;

		return simVal;
	}
}
