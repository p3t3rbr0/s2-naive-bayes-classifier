package app

import com.formdev.flatlaf.FlatIntelliJLaf
import scala.collection.MapView

import javax.swing.{JFrame, JToolBar, JButton, JTextArea, BoxLayout, JPanel, BorderFactory, Box, JLabel, ImageIcon, UIManager, WindowConstants, JScrollPane, JComboBox}
import java.awt.{EventQueue, BorderLayout, Dimension, Insets, Font}
import java.awt.event.ActionEvent

import app.classifier.NaiveBayesLearningAlgorithm


object NBAlgorithm {
  val algorithm = new NaiveBayesLearningAlgorithm

  algorithm.addExample("This is a good day", "good")
  algorithm.addExample("This is a regular day", "normal")
  algorithm.addExample("This is a bad day", "bad")

  println(algorithm.dictionary)

  val result = algorithm.classifier.classify("This is a good day")
}


object GUI {
  UIManager.setLookAndFeel(new FlatIntelliJLaf())

  // ********************************************************************************
  // Main Window
  // ********************************************************************************
  val mainWindow = new JFrame()
  mainWindow.setTitle("Naive Bayes Classifier v0.1.0")
  mainWindow.setSize(640, 480)
  mainWindow.setLocationRelativeTo(null)
  mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  // ********************************************************************************

  // ********************************************************************************
  // Toolbar
  // ********************************************************************************
  val toolbar = new JToolBar()
  toolbar.setFloatable(false)
  toolbar.setMargin(new Insets(2, 8, 2, 8))

  val addToModelBtn = new JButton(new ImageIcon("src/main/resources/add.png"))
  addToModelBtn.setToolTipText("Add example to model");
  addToModelBtn.addActionListener((e: ActionEvent) => {
    val learningWindow = new JFrame()
    learningWindow.setTitle("Learning model")
    learningWindow.setSize(800, 600)
    learningWindow.setLocationRelativeTo(null)

    val tokensTextarea = new JTextArea()
    tokensTextarea.setAlignmentX(0.0f)
    tokensTextarea.setPreferredSize(new Dimension(400, 400));
    learningWindow.add(tokensTextarea)

    val typeCBox = new JComboBox(Array("Positive", "Neutral", "Negative"))
    learningWindow.add(typeCBox)

    learningWindow.pack()
    learningWindow.setVisible(true)
  })
  toolbar.add(addToModelBtn)
  toolbar.addSeparator()

  val listModelBtn = new JButton(new ImageIcon("src/main/resources/list.png"))
  listModelBtn.setToolTipText("Show model examples");
  listModelBtn.addActionListener((e: ActionEvent) => {
    val tokensWindow = new JFrame()
    tokensWindow.setTitle("Naive Bayes Classifier v0.1.0")
    tokensWindow.setSize(800, 600)
    tokensWindow.setLocationRelativeTo(null)

    val tokensTextarea = new JTextArea()
    tokensTextarea.setAlignmentX(0.0f)
    tokensTextarea.setPreferredSize(new Dimension(200, 200));
    tokensTextarea.setText(NBAlgorithm.algorithm.dictionary.toString())
    tokensTextarea.setEditable(false)

    tokensWindow.add(tokensTextarea)
    tokensWindow.pack()
    tokensWindow.setVisible(true)
  })
  toolbar.add(listModelBtn)
  toolbar.addSeparator()

  val quitBtn = new JButton(new ImageIcon("src/main/resources/quit.png"))
  quitBtn.setToolTipText("Quit program");
  quitBtn.addActionListener((e: ActionEvent) => {
    println("Good by")
    System.exit(0)
  })
  toolbar.add(quitBtn)
  // ********************************************************************************

  // ********************************************************************************
  // MainPanel
  // ********************************************************************************
  val mainPanel = new JPanel()
  mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS))
  // ********************************************************************************

  // ********************************************************************************
  // TextPanel
  // ********************************************************************************
  val textPanel = new JPanel(new BorderLayout())
  textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
  textPanel.setAlignmentX(0.0f)
  val textarea = new JTextArea()
  textarea.setAlignmentX(0.0f)
  textarea.setPreferredSize(new Dimension(200, 200));
  val scrollPane = new JScrollPane(textarea)
  textPanel.add(scrollPane)
  textPanel.add(textarea)
  mainPanel.add(textPanel)
  // ********************************************************************************

  // ********************************************************************************
  // ActionPanel
  // ********************************************************************************
  val actionPanel = new JPanel()
  actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS))
  actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10))
  actionPanel.setAlignmentX(0.0f)

  val titleLable = new JLabel("Classifier result:")
  titleLable.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0))

  val resultLabel = new JLabel()
  resultLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0))

  val classifyBtn = new JButton(new ImageIcon("src/main/resources/run.png"))
  classifyBtn.setToolTipText("Start classification");
  classifyBtn.setText("Classify text")
  classifyBtn.addActionListener((e: ActionEvent) => {
    println("Clasify...")
    resultLabel.setText(NBAlgorithm.result)
  })

  actionPanel.add(classifyBtn)

  actionPanel.add(titleLable)
  actionPanel.add(resultLabel)

  mainPanel.add(actionPanel)
  // ********************************************************************************

  mainWindow.add(toolbar, BorderLayout.NORTH)
  mainWindow.add(mainPanel)
  mainWindow.pack()

  def show(): Unit = mainWindow.setVisible(true)
}

object Main {
  def main(args: Array[String]): Unit = {

    // ********************************************************************************
    // Classifier
    // ********************************************************************************


    GUI.show()
  }
}
