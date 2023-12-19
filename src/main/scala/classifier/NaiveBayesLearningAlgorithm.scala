package app.classifier

/**
  * Обучающий алгоритм классификации
  */
class NaiveBayesLearningAlgorithm {

  private var examples: List[(String, String)] = List()

  private val tokenize = (v: String) => v.split(' ')
  private val tokenizeTuple = (v: (String, String)) => tokenize(v._1)
  private val calculateWords = (l: List[(String, String)]) => l.map(tokenizeTuple(_).length).reduceLeft(_ + _)

  def addExample(ex: String, cl: String): Unit = {
	examples = (ex, cl) :: examples
  }

  def dictionary = examples.map(tokenizeTuple).flatten.toSet

  def model = {
	val docsByClass = examples.groupBy(_._2)
	val lengths = docsByClass.view.mapValues(calculateWords)
	val docCounts = docsByClass.view.mapValues(_.length)
	val wordsCount = docsByClass.view.mapValues(_.map(tokenizeTuple).flatten.groupBy(x => x).view.mapValues(_.length))

	new NaiveBayesModel(lengths, docCounts, wordsCount, dictionary.size)
  }

  def classifier = new NaiveBayesClassifier(model)
}
