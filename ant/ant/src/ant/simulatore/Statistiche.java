package ant.simulatore;


import java.util.*;

import ant.Ambiente;
import ant.Cibo;
import ant.Formicaio;
import ant.formica.Formica;

public class Statistiche {

	synchronized public void stampaStatisticheFinali(Ambiente ambiente) {
		final Formicaio formicaio = ambiente.getFormicaio();

		final Set<Cibo> raccolto = formicaio.getCiboRaccolto();
		System.out.println("Totale cibo presente nel formicaio: " + raccolto.size());
		System.out.println();

		// (VEDI DOMANDA 3)
		System.out.println("Quantita' raccolta da ciascuna formica:");
		final Map<Formica,Integer> formica2quantita = raccoltoPerFormica(raccolto);
		stampaRaccoltoPerFormica(formica2quantita);
		System.out.println();

		// (VEDI DOMANDA 4)
		System.out.println("Quantita' di cibo raccolto per ciascuna tipologia di formica:");
		final Map<Class<?>,Integer> tipologia2cibo = raccoltoPerTipoDiFormica(raccolto);
		stampaRaccoltoPerTipoDiFormica(tipologia2cibo);
		System.out.println();

//		 (VEDI DOMANDA 5)
		System.out.println("Classifica finale delle strategie di raccolta:");
		final List<Class<?>> classificaTipo = ordinaStrategieDiRaccolta(tipologia2cibo);
		stampaClassificaStrategie(classificaTipo,tipologia2cibo);
		System.out.println();
	}


	/**
	 * <B>DA COMPLETARE (VEDI DOMANDA 3)</B>
	 * @param raccolto - insieme di unita' di cibo raccolte
	 * @return una mappa che conti per ogni formica quante unita' di cibo ha raccolto
	 */
	public Map<Formica, Integer> raccoltoPerFormica(Set<Cibo> raccolto) {
		// DA COMPLETARE (VEDI DOMANDA 3)
		// N.B. il tipo restituito e' migliorabile dopo aver svolto la domanda 2
		Map<Formica,Integer> mappa = new HashMap<Formica,Integer>();
		int conta_cibo_per_formica = 1;
		for(Cibo cibo : raccolto) {
			if(mappa.containsKey(cibo.getRaccoglitrice())){
				conta_cibo_per_formica = mappa.get(cibo.getRaccoglitrice())+ 1;
				mappa.put(cibo.getRaccoglitrice(), conta_cibo_per_formica);
			}
			else {
				mappa.put(cibo.getRaccoglitrice(),conta_cibo_per_formica);
			}
		}
		return mappa;
	}


	/**
	 *  <EM>N.B. UTILE PER STAMPARE RISULTATI DOMANDA 3</EM>
	 * @param formica2quantita
	 */
	private void stampaRaccoltoPerFormica(final Map<Formica, Integer> formica2quantita) {
		// N.B. il tipo del parametro e' migliorabile dopo aver svolto la domanda 2
		for(Formica formica : formica2quantita.keySet()) {
			Integer quantita = formica2quantita.get(formica);
			if (quantita==null)
				quantita = 0;
			System.out.println("La formica "+formica+" ha raccolto "+quantita);
		}
	}

	/**
	 * <B>DA COMPLETARE (VEDI DOMANDA 4)</B>
	 * @param raccolto - l'insieme di unita' di cibo raccolte
	 * @return una mappa che riporta per ciascuna tipologia di formiche quante unita' di cibo ha raccolto
	 */
	
	//NON HO MANCO MEZZA IDEA PD
	public Map<Class<?>, Integer> raccoltoPerTipoDiFormica(Set<Cibo> raccolto) {
		// DA COMPLETARE (VEDI DOMANDA 4)
		Map<Formica,Integer> mappa = raccoltoPerFormica(raccolto);
		
		
		Map<Class<?>, Integer> mappa_due= new HashMap<Class<?>, Integer>();
		Set<Formica> formiche = mappa.keySet();
		for(Formica formica : formiche) {
			if(mappa_due.containsKey(formica.getClass())) {
				int cibo_raccolto = mappa.get(formica) + mappa_due.get(formica.getClass());
				mappa_due.put(formica.getClass(),cibo_raccolto);
			}
			else {
				mappa_due.put(formica.getClass(),mappa.get(formica));
			}
		}
		return mappa_due;
	}

	/**
	 *  <EM>N.B. UTILE PER STAMPARE RISULTATI DOMANDA 4</EM>
	 * @param tipo2cibo
	 */
	private void stampaRaccoltoPerTipoDiFormica(final Map<Class<?>, Integer> tipo2cibo) {
		if (tipo2cibo==null) return;

		for(Class<?> tipo : tipo2cibo.keySet()) {
			int raccolto = tipo2cibo.get(tipo);
			System.out.println("Le formiche di tipo "+tipo.getSimpleName()+" hanno raccolto "+raccolto);
		}
	}

	/**
	 * <B>DA COMPLETARE (VEDI DOMANDA 5)</B>
	 * @param c2c una mappa che per ogni tipologia di formica riporta quante unita' ha raccolto
	 * @return una lista ordinata degli oggetti {@link Class} associati ai diversi tipi di {@link Formica}
	 */
	public List<Class<?>> ordinaStrategieDiRaccolta(final Map<Class<?>, Integer> c2c) {
		// DA COMPLETARE (VEDI DOMANDA 5)
		Comparatore comparatore = new Comparatore(c2c);
		List<Class<?>> lista_della_spesa = new ArrayList<Class<?>>();
		for(Class<?> classe :c2c.keySet() ) {
			lista_della_spesa.add(classe);
		}
		Collections.sort(lista_della_spesa,comparatore);
		return lista_della_spesa;
	}

	/**
	 * <EM>N.B. UTILE PER STAMPARE RISULTATI DOMANDA 5</EM>
	 * @param classifica
	 * @param tipo2cibo
	 */
	private void stampaClassificaStrategie(List<Class<?>> classifica, Map<Class<?>, Integer> tipo2cibo) {
		if (classifica==null) return;
		
		for(int i=1; i<classifica.size()+1; i++) {
			final Class<?> tipo = classifica.get(i-1);
			System.out.println(i+") "+tipo.getSimpleName()+" con "+tipo2cibo.get(tipo)+" unita' di cibo");
		}
	}
}
