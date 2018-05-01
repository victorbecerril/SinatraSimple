package sinatraProject;

public class SinatraCreateSong extends SinatraParentTest{

	public static void main(String[] args) {
		//Navegar a Sinatra
		openSinatraSite();
		validateMainMenu();
		logIntoSite();
		//Login
		//Click en Songs
		clickSongs();
		//Validar que existe Create a new songs
		validateCreateSong();
		//create song
		createSong();
	}
	
	
	

}
