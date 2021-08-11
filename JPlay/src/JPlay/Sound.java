/*
 * The use this code commercially must only be done with permission of the author.
 * Any modification shall be advised and sent to the author.
 * The author is not responsible for any problem therefrom the use of this frameWork.
 *
 * @author Gefersom Cardoso Lima
 * Universidade Federal Fluminense - UFF - Brasil - 2010
 * Ciência da Computação
 */

package JPlay;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound
{
    AudioFormat audioFormat;
    AudioInputStream audioInputStream;
    SourceDataLine sourceDataLine;
    boolean stop;
    boolean pause;
    float volume;
    boolean volumeChanged;
    Song song;
    boolean loop;
    String fileName;

    public Sound(String fileName)
    {
            loop = false;
            this.fileName = fileName;
            load(fileName);
    }

    public void load(String fileName)
    {
            try
            {
                audioInputStream = AudioSystem.getAudioInputStream(new File(fileName));
            }
            catch (UnsupportedAudioFileException ex)
            {
                Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex)
            {
                Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
            }

            audioFormat = audioInputStream.getFormat();
            DataLine.Info dataLineInfo = new DataLine.Info( SourceDataLine.class, audioFormat);

            try
            {
                sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            }
            catch (LineUnavailableException ex)
            {
                Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public void increaseVolume(float value)
    {
            volume += value;
            volumeChanged = true;
    }

    public void decreaseVolume(float value)
    {        
            volume -= value;
            volumeChanged = true;
    }

    public void setVolume(float value)
    {
            volume = value;
            volumeChanged = true;
    }

    public void play()
    {
            if (pause == false)
            {
                    song = new Song();
                    song.start();
            }
            else
                //if (pause == true)
                    pause = false;
    }

    public void stop()
    {
            stop = true;
            if (song == null) song = null;
    }

    public void pause()
    {
            pause = true;
    }

    private class Song extends  Thread
    {
            byte tempBuffer[] = new byte[1000];

            @Override
            public void run()
            {
                try
                {
                        sourceDataLine.open(audioFormat);
                        sourceDataLine.start();

                        //while there are dates to execute and stop == false
                        int count = 0;
                        while( count != -1 && stop == false)
                        {
                                if( pause == false )
                                    count = audioInputStream.read(tempBuffer,0,tempBuffer.length);
                                else
                                    count = 0;

                                //If there are dates to execute
                                if(count > 0 )
                                {
                                    if (volumeChanged)
                                    {
                                            FloatControl volControl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
                                            volControl.setValue(volume);
                                            volumeChanged = false;
                                    }
                                    sourceDataLine.write(tempBuffer, 0, count);
                                }
                        }
                        
                        sourceDataLine.drain();
                        sourceDataLine.close();                       

                        if (loop && stop == false)
                        {
                            load(fileName);
                            setVolume(volume);
                            song = new Song();
                            song.start();
                        }
                        
                        stop = false;
                        song = null;
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
            }
    }

    public void setRepeat(boolean value)
    {
        loop = value;
    }

    public boolean isExecuting()
    {
        return song != null;
    }

}

