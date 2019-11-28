/* 
 Filter out empty playlist in constructor and then store list of PlayList to Queue to do interleaving.
 hasNextTrack() only check if queue is empty and if first playList contains any track.
 getNextTrack() return null if there's no next track or return track from the first playList in queue.
 And if there's track in the popped playList,  push it to the end of queue to do interLeaving.
*/

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
