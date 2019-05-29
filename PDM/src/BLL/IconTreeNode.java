package BLL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;

public class IconTreeNode extends DefaultMutableTreeNode {

    private static final long serialVersionUID = 1L;

    private String nodeName;

    private ImageIcon Iconfile;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public ImageIcon getIcon() {
        return Iconfile;
    }

//    public void setIcon(ImageIcon Iconfile) {
//        this.Iconfile = Iconfile;
//    }

    public IconTreeNode(Icon icon, String text, String typeicon) {
        this.nodeName = text;
        if ("Blue".equals(typeicon))  this.Iconfile = new ImageIcon(getClass().getResource("/Icon/Icon-Lock1.png"));
        if ("Red".equals(typeicon))   this.Iconfile = new ImageIcon(getClass().getResource("/Icon/Icon-Lock2.png"));
        setUserObject(new TreeNodeData(icon, text));
    }

    //Lớp internal
    class TreeNodeData {

        Icon icon;
        String text;
        public TreeNodeData(Icon icon, String text) {
            this.icon = icon;
            this.text = text;
        }

        public Icon getIcon() {
            return icon;
        }

        public void setIcon(Icon icon) {
            this.icon = icon;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
