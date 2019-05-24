package form;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Luciver
 *
 */
public class ChangeTheme {
    private UIManager.LookAndFeelInfo[] lookFeel =null;
    private String[] INmae = null;
    public ChangeTheme(){
        lookFeel = UIManager.getInstalledLookAndFeels();
        INmae = new String[lookFeel.length];
        for(int i=0; i < lookFeel.length;i++){
            INmae[i]= lookFeel[i].getName();
        }
    }
    public void setTheme(int index){
        try{
            UIManager.setLookAndFeel(lookFeel[index].getClassName());
        }catch(ClassNotFoundException | InstantiationException |IllegalAccessException | UnsupportedLookAndFeelException ex ){
        }
    }
}
