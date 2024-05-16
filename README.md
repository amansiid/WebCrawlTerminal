# Web Crawler Application

The Web Crawler Application is a Java-based tool that allows users to crawl web pages and analyze their link structures and closeness centrality. It runs easily using the terminal
and displays a beautiful interactive graph, as well as the URL's and their closeness scores. 

## Introduction

As stated above, this program runs using Java to crawl web pages and display their closeness centrality based on how the graph is connected. The point of this program 
was to discover and grow our knowledge in languages we are not as familiar with, as well as learn what data structures and libraries exist for them. In addition, it 
allowed us to apply some of the learnings from our Algorithms class into a real world program. In summary, this project furthered our understanding of Java and 
its libaries, helped us familiarize ourselves with various concepts that exist in Java, and apply a few of the algorithms we learned during our class. 

## Features

- **Web Crawling**: Input the path to a file with the URL's you wish to crawl. 
- **Crawling Depth**: Specify the depth of crawling to control the extent of the exploration.
- **Graph Visualization**: Visualize the link structure as a graph for better understanding.
- **Closeness Centrality**: Calculate and display the closeness centrality of each node in the terminal for the graph.
- **Export to CSV**: Export the results, including URLs and their closeness centrality values, to a CSV file for further analysis.

## Description

After the user clones the repository and compiles the files. They can run the program using normal Java commands in the terminal. When the program runs
it will ask the user for a file path to a .txt with the URL's they wish to crawl. The file path MUST be a complete file path (this can be done by right
clicking in the directory where the file is, and clicking 'path'). After, the user will be prompted for a depth they wish to crawl from the webpage (this
value MUST be an positive integer). Once this is done, the program will crawl the webpages, parsing the HTML files for hrefs (links), and build an 
adjaceny list. Once that is complete, the program will display a fascinating interactive directed graph in a seperate window showing the webpages as nodes, and which pages
they lead to with arrow directed edges. The time to display the graph varies depending on how large the webpage is, and to what depth the user wishes 
to crawl. In the terminal, you will also see a list displayed featuring the URL's and their closeness score. This score is based on the widely accepted formula. 
It is slighlty adjusted for nodes that can reach at least 75% of the nodes in the graph, displaying a score that reflects what they can reach. The program will 
also create a .csv file in the same directory as the source code that containes the URL's and their scores if the user wishes to use those results for 
anything else. 

## Requirments

1. **Java Development Kit (JDK):**
    - The newest version of Java will most likely be required to compile and run the program.
    - Download and installation instructions can be found [here](https://www.oracle.com/java/technologies/downloads/).

2. **External Libararies (JAR Files)**
    - The program depends on external libraries, those can be found in the 'lib' directory.
    - Include all JAR files in the classpath when running the program. 

3. **txt File**
    - This program requires a user to pass in a .txt file as their first argument containing links they
      wish to crawl. There is a txt file included within the directory, you can add or delete anything to
      it as you wish. The prompt for the program MUST be the full path to the file.

## Installation And User Manual

1. **Clone the Repository**
    - Clone this repository to your local machine.

2. **Compile the the files**
    - Open the terminal on your machine.
    - Navigate to the directory where you cloned the repository.
    - Once you are there, input the command: 'javac -d bin -cp "lib/*" src/webpackage/*.java'
    - This will create .class files in the bin folder (those may already be there but run it anyways).
    - Next, input the command: 'java -cp bin;lib/* webpackage.WebCrawlerTerminal'
    - If you are on MacOS or Linux, the command should be: 'java -cp bin:lib/* webpackage.WebCrawlerTerminal'
    - Then you should be prompted to input a path to a file (This should be the full path).
    - Next you will be asked for a depth (this must be an integer).
    - A graph will open in a seperate window, closeness scores and their URL's will be displayed in the 
      terminal, and a .csv will be saved inside the code directory. 
    - That should be all, re-run the program and have fun!

## Usage

1. **Input URLs**: Enter URLs directly into the text area or upload a text file containing URLs.
2. **Set Crawling Depth**: Specify the depth of crawling to control how deep the crawler explores.
3. **Start Crawling**: Click on the "Crawl" button to start the crawling process.
4. **Visualize Results**: Explore the graph visualization to understand the link structure.
5. **Export Results**: Optionally, export the results to a CSV file for further analysis.

## Reflection 

Developing a comprehensive and deployable software solution entails navigating through various stages, each presenting its unique set of challenges and learning opportunities. Our journey in building this program was no exception, as we encountered hurdles during different phases of development, including design, implementation, packaging, and deployment.

At the outset of the project, we embarked on the task of designing a robust and efficient program that would fulfill its intended purpose effectively. This involved brainstorming sessions to outline the program's features, defining the requirements, and mapping out the architecture and functionality. Transitioning from C++ to Java brought its own challenges, as we had to adapt to the nuances and conventions of a new programming language. However, we found many similarities between the two languages, which eased the transition process and allowed us to leverage our existing knowledge and skills.

As we delved deeper into the project, one of the primary challenges we encountered was understanding and analyzing the time complexity of the algorithms we implemented. Time complexity is a critical factor in determining the efficiency of a program, influencing the choice of algorithms and optimization techniques. We spent considerable time dissecting and evaluating the algorithms to ensure they met the performance requirements and could handle large datasets efficiently.

Our algorithmic analysis revealed that certain operations, such as parsing input files and crawling web pages, appeared to operate in linear time complexity (O(n)), reflecting their relatively straightforward nature. However, constructing the adjacency list and graph structure introduced complexities due to recursive operations. While the creation of nodes seemingly operates in linear time (O(n)), establishing edges involves more intricate operations, possibly resulting in a time complexity of O(m) or O(n^2), where 'm' represents the number of edges.

The calculation of the closeness centrality algorithm posed one of the most formidable challenges. The algorithm comprises multiple nested loops, with the largest one potentially exhibiting a cubic time complexity (O(n^3)). Considering these calculations, the overall time complexity of the program could be approximated as O((n^3) + m), where 'n' represents the number of nodes and 'm' denotes the number of edges.

A significant hurdle we faced during the development process was the generation of a JAR (Java ARchive) file for our project. The JAR file serves as a container for Java classes and resources, making it easier to distribute and run Java applications on different platforms. Despite our efforts to generate the JAR file successfully, we encountered various technical issues and compatibility issues that hindered our progress. This challenge prompted us to explore alternative approaches to packaging and deploying our application.

After careful consideration, we made the decision to transition our project to a terminal-based application, which offered a more straightforward deployment process. This transition required us to refactor our codebase to remove any dependencies on graphical user interface (GUI) components and instead focus on implementing a command-line interface (CLI) for interacting with the program. While transitioning to a terminal-based application posed its own set of challenges, including adapting the user interaction flow and handling input/output operations differently, it proved to be a valuable learning experience.

Throughout the development process, we encountered additional challenges, such as integrating external libraries like Jsoup for parsing and GraphStream for graph visualization. These libraries enriched the functionality of our program, enabling efficient web crawling and dynamic graph visualization. However, integrating them into our project required careful attention to detail and thorough testing to ensure compatibility and seamless operation.

In conclusion, the journey of developing this program has been both challenging and rewarding, offering valuable insights into Java programming, algorithm design, and software development practices. Despite the hurdles encountered along the way, we persevered and successfully navigated through each stage of development, emerging with a deeper understanding of software engineering principles and a stronger foundation for future projects. As we continue to grow and evolve as developers, we carry forward the lessons learned from these challenges, knowing that they have contributed to our growth and development in the field of software engineering.

You can find a video of this program running [here](https://youtu.be/QkTB5Eu-vDA).

## Results
![terminalapp](https://github.com/csc3430-winter2024/web-crawler-term-and-graph-16/assets/83798920/c6827b52-fc1e-4828-9d19-4d5e72bdb0fd)
![graph](https://github.com/csc3430-winter2024/web-crawler-term-and-graph-16/assets/83798920/e982814c-5139-419e-85b7-6169368781c8)
![movinggrapoh](https://github.com/csc3430-winter2024/web-crawler-term-and-graph-16/assets/83798920/aa833524-3a2a-45c6-b75a-46009d5842e7)
