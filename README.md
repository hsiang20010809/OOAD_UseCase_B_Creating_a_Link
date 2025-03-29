## 繼OOAD_UseCase_A_Creating_an_object的續做

新增功能:

● 於basic object上繪製connection link

## 功能

繪製圖形：
支援在畫布上繪製矩形（Rect）和橢圓（Oval）。

物件選取：
點擊畫布上的物件可選取該物件，並以紅色邊框標記。
當物件重疊時，優先選取最上層的物件。

物件深度：
矩形的深度比橢圓淺（矩形會覆蓋在橢圓之上，但選取時優先選取橢圓）。

連接線選擇:

1.association : 最基本的箭頭連接線，指向目標位置

2.generalization : 三角形箭頭連接線，同樣指向目標位置

3.composition : 菱形箭頭，同樣指向目標位置

## 操作說明

繪製矩形：
點擊工具列上的 Rect 按鈕，然後在畫布上點擊，將在點擊位置繪製一個矩形。

繪製橢圓：
點擊工具列上的 Oval 按鈕，然後在畫布上點擊，將在點擊位置繪製一個橢圓。

選取物件：
點擊工具列上的 Select 按鈕，然後點擊畫布上的物件進行選取。
若多個物件重疊，會優先選取最上層的物件。

繪製連接線:
選取你要的連接線，
點擊工具列上的 Select 按鈕，然後按著滑鼠左鍵不放，直至移動到你要連接的物件上。

## 專案結構

主程式：
WorkflowEditor.java：初始化圖形編輯器的主視窗，包含工具列與畫布。

核心邏輯：
Canvas.java：負責繪製圖形與處理滑鼠事件，包含物件深度排序與選取邏輯。

Shape.java：抽象類別，定義圖形的基本屬性與方法。

圖形類別：
Rect.java：矩形物件的實現。
Oval.java：橢圓物件的實現。

圖形連接:
Link.java : 負責定義圖形之間的連結，包含連結的起點、終點及類型（如 Association、Generalization、Composition），並提供繪製連結的功能。

輔助類別：
Button.java：定義工具列按鈕的模式（如 RECT、OVAL、SELECT）。
