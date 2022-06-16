package ant.formica;

import static ant.costanti.CostantiGUI.IMMAGINE_FORMICA_ROSSA;

import java.awt.Image;
import java.util.Set;

import ant.Ambiente;
import ant.Coordinate;
import ant.Direzione;

public class Aggressiva extends Formica{
	
	static private int progId=0;
	
	final private int id;
	

	public Aggressiva(Ambiente ambiente) {
		super(ambiente);
		this.id = progId++;
	}

	public int getId() {
		return this.id;
	}




	@Override
	public Image getImmagine() {
		return IMMAGINE_FORMICA_ROSSA;
	}
	
	
	@Override
	public boolean decideDiCambiareDirezione() {
		return true;
	}

	@Override
	public Direzione cambioDirezione(Set<Direzione> possibili) {
		Direzione direzione = this.getAmbiente().getDirezioneCiboVicino(this.getPosizione());
		if (direzione!=null)
			/* insisti, c'e' una traccia chimica da seguire: non cambiare direzione */
			return direzione; 
		
		/* non c'e' traccia chimica nella direzione corrente; cerca il ferormone vicino */
		else /* traccia chimica persa... */
			return Direzione.scegliAcasoTra(possibili);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName()+getId();
	}

}
