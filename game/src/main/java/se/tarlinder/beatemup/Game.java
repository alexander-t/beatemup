package se.tarlinder.beatemup;

import se.tarlinder.ge2d.Engine;
import se.tarlinder.ge2d.Entity;
import se.tarlinder.ge2d.animation.Animation;
import se.tarlinder.ge2d.animation.AnimationController;
import se.tarlinder.ge2d.sprite.SpriteCache;
import se.tarlinder.ge2d.sprite.SpriteSheet;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    private Engine engine;

    public Game() {
        getContentPane().add(new Canvas());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        loadSprites();

        Animation idleAnimation = new Animation(true);
        idleAnimation.addStep("idle", 1000);

        Animation animation = new Animation(true);
        animation.addStep("walk 1", 175);
        animation.addStep("walk 2", 175);
        animation.addStep("walk 3", 175);
        animation.addStep("walk 4", 175);
        animation.addStep("walk 5", 175);
        animation.addStep("walk 6", 175);

        Entity player = new Entity();
        player.addComponent(new PlayerMovement());
        player.addComponent(new AnimationController(animation, idleAnimation));

        engine = new Engine(this);
        engine.addEntity(player);
        engine.startGameLoop();
    }

    private void loadSprites() {
        SpriteSheet haggarSheet = new SpriteSheet("/Haggar_v1.png");
        haggarSheet.makeColorTransparent(new Color(88, 184, 248));
        SpriteCache.add("idle", haggarSheet.cutOut(4, 7, 60, 94));
        SpriteCache.add("walk 1", haggarSheet.cutOut(1, 109, 60, 96));
        SpriteCache.add("walk 2", haggarSheet.cutOut(67, 110, 60, 96));
        SpriteCache.add("walk 3", haggarSheet.cutOut(138, 110, 60, 95));
        SpriteCache.add("walk 4", haggarSheet.cutOut(204, 112, 60, 96));
        SpriteCache.add("walk 5", haggarSheet.cutOut(275, 112 - 1, 60, 96));
        SpriteCache.add("walk 6", haggarSheet.cutOut(344, 110, 60, 96));
    }

    public static void main(String[] args) {
        new Game();
    }

    class Canvas extends JPanel {

        public Canvas() {
            setPreferredSize(new Dimension(300, 300));
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (engine != null) {
                engine.render(g);
            }
        }
    }
}
