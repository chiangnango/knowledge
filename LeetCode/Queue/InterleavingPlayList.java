import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public interface Playlist {
    @Nullable Track getNextTrack();
    boolean hasNextTrack();
}

// PlayListA: 123
// PlayListB: 456
// getNextTrack() -> 142536
public class InterleavingPlaylist implements Playlist {
    
    private LinkedList<PlayList> queue = new LinkedList();

    public InterleavingPlaylist(List<Playlist> playlists) {
        for (PlayList playList : playlists) {
            if (playList.hasNextTrack()) {
                queue.add(playList);
            }
        }
    }
    
    @Nullable
    @Override
    public Track getNextTrack() {
        if (!hasNextTrack()) {
            return null;
        }
        
        PlayList playList = queue.removeFirst();
        Track track = playList.getNextTrack();
        if (playList.hasNextTrack()) {
            queue.add(playList)
        }
        return track;
    }
    
    @Override
    public boolean hasNextTrack() {
        if (queue.isEmpty()) {
            return false;
        }
        
        PlayList playList = queue.getFirst();
        return playList.hasNextTrack()
    }
}
