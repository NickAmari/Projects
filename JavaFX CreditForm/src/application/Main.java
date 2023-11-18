package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.StageStyle;


public class Main extends Application {
	
    int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
    
    Stage stage;
    Scene scene;
    
    int initialX;
    int initialY;
    
	@Override
	public void start(Stage primaryStage) {
		try {
			Assignment3 root = new Assignment3();

	        int sceneWidth = 0;
	        int sceneHeight = 0;
	        if (screenWidth <= 800 && screenHeight <= 600) {
	            sceneWidth = 600;
	            sceneHeight = 350;
	        }
	        else if (screenWidth <= 1280 && screenHeight <= 768) {
	            sceneWidth = 800;
	            sceneHeight = 450;
	        }
	        else if (screenWidth <= 1920 && screenHeight <= 1080) {
	            sceneWidth = 1000;
	            sceneHeight = 650;
	        }

	        // Scene
	        stage = new Stage();
	        stage.initStyle(StageStyle.TRANSPARENT);
	        scene = new Scene(root, sceneWidth, sceneHeight, Color.TRANSPARENT);


	        stage.setScene(scene);
	        
	        //event handler
	        
	        stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
	            if (KeyCode.ESCAPE == event.getCode()) {
	                stage.close();
	            }
	        });
	        
	        stage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
