# Apache OpenNLP model checker
A simple tool that allows you to check OpenNLP model on your data set.

## How to start using it?
* Clone or download this repository from GitHub.
* Make sure that you have Java installed `java --version`, otherwise download it from [Java official website](https://java.com/en/download/)
* In `classes/artifacts/opennlp_categorizer__jar` folder start Command Prompt and execute `java -jar opennlp-categorizer.jar model_path test_file output_file amount`, where:
    * __model_path__ - path to trained model
    * __test_file__ - path to file with data to check. Every new text for analysis should be on a new line and each line is represented as follows: `mark[Tab]text`
    * __output_file__ - path to file where results will be saved. Format for each line is - `real_mark[Tab]predicted_mark[Tab]text[Tab]text_length[Tab]amount_parameter`
    * __amount__ - number, that is used to separate results (_It does not affect sentiment analysis results_)