package study0830.playable;

class PlayMusic implements Playable {
	public void play() {
		System.out.println("PlayMusic");
	}
}
class PlayVideo implements  Playable{
	public void play() {
		System.out.println("play video");
	}
}


public class Play {
	public static void main(String[] args) {
		Playable playMusic = new PlayMusic();
		Playable playVideo = new PlayVideo();
		
		playMusic.play();
		playVideo.play();
		
		Playable[] pa = {new PlayMusic(), new PlayVideo()};
		
		for (int i=0; i<pa.length; i++) {
			System.out.println("i : " +  i);
			pa[i].play();
		}
	}
}
