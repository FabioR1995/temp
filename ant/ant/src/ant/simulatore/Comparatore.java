package ant.simulatore;

import java.util.Comparator;
import java.util.Map;

public class Comparatore implements Comparator<Class<?>> {
	private Map<Class<?>,Integer> mappa;
	
	public Comparatore(Map<Class<?>,Integer> mappone) {
		this.mappa = mappone;
	}
	
	public int compare(Class<?> classe_uno, Class<?> classe_due) {
		int comp = this.mappa.get(classe_uno)- this.mappa.get(classe_due);
		if(comp==0)
		      comp = classe_uno.getClass().getName().compareTo(classe_due.getClass().getName());
		return comp;
	}
}
