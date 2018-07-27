package com.cm55.depDetect.gui.depends;

import java.util.stream.*;

import com.cm55.depDetect.gui.common.*;
import com.cm55.depDetect.gui.model.*;
import com.cm55.fx.*;
import com.google.inject.*;

/**
 * 全パッケージ表示パネル
 * @author ysugimura
 */
public class AllPackagesPanel implements FxNode {

  FxTitledBorder titledBorder;
  PackagesPanel packagesPanel;
  
  @Inject
  public AllPackagesPanel(Model model) {
    packagesPanel = new PackagesPanel();
    titledBorder = new FxTitledBorder("対象パッケージ", new FxJustBox(packagesPanel));
    model.listen(ModelEvent.ProjectChanged.class,  this::projectChanged);
    
  }
  
  void projectChanged(ModelEvent.ProjectChanged e) {
    packagesPanel.setRows(e.descendSet.getEffectivePackages(e.root).collect(Collectors.toList()));
  }

  public javafx.scene.Node node() {
    return titledBorder.node();
  }
}
