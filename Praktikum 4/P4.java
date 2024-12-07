/* Generated By:JavaCC: Do not edit this line. P4.java */
        public class P4 implements P4Constants {
        private static SymbolTable.SymbolTable symbolTable = new SymbolTable.SymbolTable();

                public static void main (String args []) {
                        P4 parser = new P4(System.in);
                        try {
                //Hier Parser starten und Ergebnis ausgeben
                                String result = parser.start();
                System.out.println(result);
                        } catch (ParseException e) {
                                System.err.println(e);
                        }
                }

  static final public String PROGRAM() throws ParseException {
 String varDecl, statement;
    CONSTDECL();
    varDecl = VARDECL();
    statement = STATEMENT();
      {if (true) return varDecl + statement;}
    throw new Error("Missing return statement in function");
  }

//Code zu Konstanten ist der gleiche wie in P3
  static final public void CONSTDECL() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FINAL:
      jj_consume_token(FINAL);
      jj_consume_token(INT);
      CONSTZUW();
      CONSTLIST();
      jj_consume_token(14);
      break;
    default:
      jj_la1[0] = jj_gen;
      ;
    }
  }

  static final public void CONSTZUW() throws ParseException {
  String constName, constValue;
    jj_consume_token(IDENT);
      constName = token.image;
    jj_consume_token(15);
    jj_consume_token(NUMBER);
        constValue = token.image;
        try {
          symbolTable.addConstant(constName, constValue);
        } catch(Exception e){
          System.err.println(e.getMessage());
          {if (true) throw new Error(e);}
        }
  }

  static final public void CONSTLIST() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 16:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      jj_consume_token(16);
      CONSTZUW();
    }
  }

  static final public String VARDECL() throws ParseException {
 String varDecl = "";
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      jj_consume_token(INT);
          varDecl = VARZUW() + VARLIST();
      jj_consume_token(14);
      break;
    default:
      jj_la1[2] = jj_gen;
      ;
    }
     {if (true) return varDecl;}
    throw new Error("Missing return statement in function");
  }

  static final public String VARZUW() throws ParseException {
  String varName, varValue, varIndex, result = "";
    jj_consume_token(IDENT);
        varName = token.image;
        //fügt die Variable in die Hashtable ein und speichert sich den Index
        try{
            varIndex = symbolTable.addVariable(varName);
            result = "10 00 36 " + varIndex + " "; //falls danach kein Wert mehr angelegt wird, dann wird mit 0 initialisiert
        } catch(Exception e){
             System.err.println(e.getMessage());
             {if (true) throw new Error(e);}
        }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 15:
      jj_consume_token(15);
      jj_consume_token(NUMBER);
            //Falls variable doch initialisiert wird, dann erstelle hier den passenden code
            varValue = token.image;
            result = "10 " + String.format("%02x", Integer.parseInt(varValue)) + " 36 " + varIndex + " ";
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
      {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  static final public String VARLIST() throws ParseException {
 String varDecl = "";
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 16:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_2;
      }
      jj_consume_token(16);
          varDecl = varDecl + VARZUW();
    }
      {if (true) return varDecl;}
    throw new Error("Missing return statement in function");
  }

  static final public String EXPRESSION() throws ParseException {
  String term, summe;
    term = TERM();
    //term wird hier SUMME übergeben, damit der linke Operand in der Methode auch zugänglich ist
        summe = SUMME(term);
      {if (true) return summe;}
    throw new Error("Missing return statement in function");
  }

//Codeerzeugung genau wie Postfixtrafo aus P3
  static final public String SUMME(String term) throws ParseException {
  String left, right, op = null;
      left = term;
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 17:
      case 18:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 17:
        jj_consume_token(17);
        break;
      case 18:
        jj_consume_token(18);
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
          op = token.image;
      right = TERM();
            //60 für Addition, 64 für Multiplikation
            if(op.equals("+")){
                left = left + right + "60 ";
            } else{
                left = left + right + "64 ";
            }
    }
      {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

  static final public String TERM() throws ParseException {
  String faktor, product;
    faktor = FAKTOR();
    product = PRODUCT(faktor);
      {if (true) return product;}
    throw new Error("Missing return statement in function");
  }

  static final public String PRODUCT(String faktor) throws ParseException {
  String left, right, op = null;
      left = faktor;
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 19:
      case 20:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 19:
        jj_consume_token(19);
        break;
      case 20:
        jj_consume_token(20);
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
          op = token.image;
      right = TERM();
            if(op.equals("*")){
                left = left + right + "68 ";
            } else{
                left = left + right + "6c ";
            }
    }
      {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

  static final public String FAKTOR() throws ParseException {
  String number, ident, expression;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUMBER:
      jj_consume_token(NUMBER);
        //im Falle einer Zahl die Zahl einfach alls Hexwert zurückgeben
        number = token.image;
        {if (true) return String.format("%02x", Integer.parseInt(number)) + " ";}
      break;
    case IDENT:
      jj_consume_token(IDENT);
        ident = token.image;
        try{
            //prüft ob der Identifier eine Variable ist
            boolean isVariable = symbolTable.isVariable(ident);
            String hashMapValue = symbolTable.getSymbol(ident);
            //Variable = erzeug 15 für LOAD, Konstante = 10 für Laden der Konstante
            {if (true) return (isVariable ? "15 " : "10 ") + hashMapValue + " ";}
        } catch(Exception e){
            System.err.println(e.getMessage());
            {if (true) throw new Error(e);}
        }
      break;
    case 21:
      jj_consume_token(21);
      expression = EXPRESSION();
      jj_consume_token(22);
                                        {if (true) return expression;}
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public String STATEMENT() throws ParseException {
 String expression;
    jj_consume_token(PRINT);
    jj_consume_token(21);
    expression = EXPRESSION();
    jj_consume_token(22);
    jj_consume_token(14);
      {if (true) return expression + "b8 (print) b1 ";}
    throw new Error("Missing return statement in function");
  }

  static final public String start() throws ParseException {
  String result;
    result = PROGRAM();
    jj_consume_token(0);
            {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public P4TokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[10];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x20,0x10000,0x40,0x8000,0x10000,0x60000,0x60000,0x180000,0x180000,0x203000,};
   }

  /** Constructor with InputStream. */
  public P4(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public P4(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new P4TokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public P4(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new P4TokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public P4(P4TokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(P4TokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List jj_expentries = new java.util.ArrayList();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[23];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 10; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 23; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

        }
