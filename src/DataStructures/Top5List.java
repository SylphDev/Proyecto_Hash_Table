package DataStructures;

public class Top5List {
    
    private HashNode first;
    private HashNode last;
    private int size;
    private int max;
    
    public Top5List(){
        this.first = null;
        this.last = null;
        this.size = 0;
        this.max = 5;
    }

    public HashNode getFirst() {
        return this.first;
    }

    public void setFirst(HashNode first) {
        this.first = first;
    }

    public HashNode getLast() {
        return this.last;
    }

    public void setLast(HashNode last) {
        this.last = last;
    }
    
    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public boolean isEmpty(){
        return first == null;
    }
    
    public HashNode getNode(int position){
        HashNode aux = this.first;
        for (int i = 0; i < this.size; i++){
            if (i == position){
                return aux;
            }else{
                aux = aux.getNextList();
            }
        }
        return null;
    }
    
    public boolean checkWord(String word){
        boolean flag = false;
        HashNode aux = this.first;
        for (int i = 0; i < this.size; i++){
            if (aux.getWord().equals(word)){
                flag = true;
                break;
            }else{
                aux = aux.getNextList();
            }
        }
        return flag;
    }
    
    public void changePositions(HashNode aux, int position){
        if (position == this.size - 1){
            HashNode proxy = this.getNode(position - 1);
            this.last.setNextList(this.first);
            this.setFirst(this.last);
            this.setLast(proxy);
        }else if (position > 0 && position < this.size - 1){
            HashNode proxy = this.getNode(position - 1);
            HashNode temp = new HashNode();
            temp.setNextList(aux.getNextList());
            aux.setNextList(this.first);
            this.setFirst(aux);
            proxy.setNextList(temp.getNextList());
            temp = null;
        }
    }
    
    public void addEmpty(HashNode newNode){
        if (isEmpty()){
            first = newNode;
            last = newNode;
            size = size + 1;
        }
    }
    
    public void addFirst(HashNode newNode){
        if (isEmpty()){
            this.addEmpty(newNode);
        }else{
            newNode.setNextList(first);
            first = newNode;
            size = size + 1;
        }
    }
    
    public void addLast(HashNode newNode){
        if (isEmpty()){
            this.addEmpty(newNode);
        }else{
            last.setNextList(newNode);
            last = newNode;
            size = size + 1;
        }
    }
    
    public void addNode(HashNode newNode, int position){
        if (isEmpty()){
            addEmpty(newNode);
        }else{
            if (position == 0){
                addFirst(newNode);
            }else if(position == size){
                addLast(newNode);
            }else{
                HashNode aux = this.getNode(position - 1);
                newNode.setNextList(aux.getNextList());
                aux.setNextList(newNode);
                size = size + 1;
            }
        }
    }
    
    public void addOrdered(HashNode newNode){
        HashNode aux = first;
        boolean flag = false;
        if (isEmpty()){
            addFirst(newNode);
        }else{
            if (this.checkWord(newNode.getWord())){
                for (int i = 0; i < this.size; i++){
                    if (aux.getWord().equals(newNode.getWord())){
                        this.changePositions(aux, i);
                        break;
                    }else{
                        aux = aux.getNextList();
                    }
                }
            }else{
                for (int i = 0; i < size; i++){
                    if (aux.getCount() < newNode.getCount()){
                        addNode(newNode, i);
                        if (this.size >= this.max){
                            this.deleteLast();
                        }
                        flag = true;
                        break;
                    }else{
                        aux = aux.getNextList();
                    }
                }
                if (!flag && this.size < this.max){
                    addLast(newNode);
                }
            }
        }
    }
    
    public void deleteLast(){
        if (isEmpty()){
        }else{
            last = getNode(size - 2);
            last.setNextList(null);
            size = size - 1;           
        }
    }
    
    public String showList(){
        String top5 = "";
        HashNode aux = this.first;
        for (int i = 0; i < this.getSize(); i++){
            top5 += "Word: " + aux.getWord() + 
                    ". Count: " + aux.getCount() + "\n";
            aux = aux.getNextList();
        }
        
        return top5;
    }
    
    public boolean checkOrder(){
        boolean flag = true;
        HashNode aux = this.first;
        for (int i = 0; i < this.size - 1; i++){
            if (aux.getCount() < aux.getNextList().getCount()){
                flag = false;
                break;
            }else{
                aux = aux.getNextList();
            }
        }
        
        return flag;
    }
    
    public void sortList(){
        while (!this.checkOrder()){
            HashNode actual = this.first;
            HashNode nextNode = this.first.getNextList();
            for (int i = 0; i < 4; i++){
                if (nextNode != null){
                    if (i == 0){
                        if (actual.getCount() < nextNode.getCount()){
                            HashNode aux = new HashNode();
                            aux.setNextList(nextNode);
                            actual.setNextList(nextNode.getNextList());
                            nextNode.setNextList(actual);
                            this.first = nextNode;
                            aux = null;
                            break;
                        }
                    } else if (i == 1){
                        if (actual.getCount() < nextNode.getCount()){
                            HashNode aux = new HashNode();
                            aux.setNextList(nextNode);
                            actual.setNextList(nextNode.getNextList());
                            nextNode.setNextList(actual);
                            this.first.setNextList(nextNode);
                            aux = null;
                            break;
                        }
                    } else if (i == 2){
                        if (actual.getCount() < nextNode.getCount()){
                            HashNode aux = new HashNode();
                            aux.setNextList(nextNode);
                            actual.setNextList(nextNode.getNextList());
                            nextNode.setNextList(actual);
                            this.first.getNextList().setNextList(nextNode);
                            aux = null;
                            break;
                        }
                    }else if (i == 3){
                        if (actual.getCount() < nextNode.getCount()){
                            nextNode.setNextList(actual);
                            this.first.getNextList().getNextList().setNextList(nextNode);
                            actual.setNextList(null);
                            this.last = actual;
                            break;
                        }
                    }
                    nextNode = nextNode.getNextList();
                    actual = actual.getNextList();
                }
            }
        }
    }
    
    public void sortMaxMin(){
        String auxWord;
        int auxCounter;
        HashNode auxNext;
        if (size > 0){
            if (first.getNextList() != null){
                HashNode ant = first;
                HashNode next = first.getNextList();
                while (next != null){
                    if (ant.getCount() < next.getCount()){
                        auxWord = next.getWord();
                        auxCounter = next.getCount();
                        auxNext = next.getNext();
                        
                        next.setWord(ant.getWord());
                        next.setCount(ant.getCount());
                        next.setNext(ant.getNext());
                        
                        ant.setWord(auxWord);
                        ant.setCount(auxCounter);
                        ant.setNext(auxNext);
//                        aux = next;
//                        next = ant;
//                        ant = aux;
                        if(next == first.getNextList()){
                            ant = next;
                            next = next.getNextList();
                        } else {
                            ant = first;
                            next = first.getNextList();
                        }
                    } else {
                        ant = next;
                        next = next.getNextList();
                    }
                }
            }
        }
    }
    
}