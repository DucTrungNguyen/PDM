package BLL;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Component;

public class IconTreeNodeRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 1L;

        @Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
		
		IconTreeNode.TreeNodeData data = (IconTreeNode.TreeNodeData) ((IconTreeNode) value).getUserObject();
		
		setIcon(data.getIcon());
		
		setText(data.getText());
                
		return this;
		
	}
}