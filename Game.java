package gameCommons;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import environment.Environment;
import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

import javax.swing.*;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
	public int chrono = 0;
	public int score;
	// Lien aux objets utilisés
	public Environment EnvInf;
	private IFrog frog;
	private IFroggerGraphics graphic;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant deplacement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.score = 0;

	}

	/**
	 * Lie l'objet frog à la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	/*public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}*/
	public void setEnvironment(Environment environment) {
		this.EnvInf = environment;
	}

	/**
	 * Définie la partie en tant que partie infini
	 *
	 * @param
	 */
	public boolean isInfinite() {
		return this.frog.getClass().getName().equals("frog.FrogInf");
	}


	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un ecran de fin approprie si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		if (this.isInfinite()) {
			if (!this.EnvInf.isSafe(this.frog.getPosition())) {
				this.graphic.endGameScreen("Game over Score : " + this.score + " " + this.chrono + "s");
				return true;
			}
		} else if (!this.EnvInf.isSafe(this.frog.getPosition())) {
			this.graphic.endGameScreen("Game over " + this.chrono + "s");
			return true;
		}
		/*
		if(this.environment.isSafe(this.frog.getPosition())) {
			return false;
		}else {
			this.graphic.endGameScreen("NUL !");
			return false;
		} */

		return false ;
	}

	/**
	 * Teste si la partie est gagnee et lance un ecran de fin approprie si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagnee
	 */
	/*
	public boolean testWin() {
		if(this.environment.isWinningPosition(this.frog.getPosition())) {
			this.graphic.endGameScreen("GG");
			return true;
		}else {
			return false;
		}
	}
	*/
	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		graphic.clear();
		EnvInf.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
		testLose();
		//testWin();
		new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				++Game.this.chrono;
			}
		});
	}
	
}
