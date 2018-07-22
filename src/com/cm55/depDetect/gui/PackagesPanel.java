package com.cm55.depDetect.gui;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import com.cm55.depDetect.*;
import com.cm55.fx.*;
import com.cm55.fx.FxTable.*;

public class PackagesPanel implements FxNode {

  FxTable<PkgNode>table;
  FxObservableList<PkgNode>rows;
  Consumer<PkgNode>selectionCallback;
  
  @SuppressWarnings("restriction")
  public PackagesPanel() {
    table = new FxTable<PkgNode>();
    table.setColumns(new FxTable.TextColumn<PkgNode>("パッケージ", t->FixedValue.w(t.getPath())).setPrefWidth(400));
    rows = table.getRows();
    table.getSelectionModel().listenSelection(e-> {
      PkgNode node = null;
      if (e.value >= 0) node = rows.get(e.value);
      if (selectionCallback != null) selectionCallback.accept(node);
    });
  }
  
  public PackagesPanel setSelectionCallback(Consumer<PkgNode>callback) {
    this.selectionCallback = callback;
    return this;
  }
  
  public void clearRows() {
    rows.clear();
  }
  
  public void setRows(Stream<PkgNode>stream) {
    setRows(stream.collect(Collectors.toList()));
  }
  
  public void setRows(Collection<PkgNode>list) {
      rows.clear();
      rows.addAll(list);
  }
  
  public javafx.scene.Node node() {
    return table.node();
  }
}
