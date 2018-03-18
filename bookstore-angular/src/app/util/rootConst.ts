export class RootConst {
  public WEB_SERVICE_ENDPOINT = 'http://localhost:8080';
  public HOME = '';
  public LOGIN_SUCCESS ='home';
  public SERVER_LOGIN = this.WEB_SERVICE_ENDPOINT + '/login';
  public ADD_USER = this.WEB_SERVICE_ENDPOINT + '/addUser?username=';
  public DELETE_USER = this.WEB_SERVICE_ENDPOINT + '/deleteUser?username=';
  public UPDATE_USER = this.WEB_SERVICE_ENDPOINT + '/updateUser?username=';
  public VIEW_USER = this.WEB_SERVICE_ENDPOINT + '/viewUser?username=';
  public ADD_ITEM = this.WEB_SERVICE_ENDPOINT + '/addItem?username=';
  public DELETE_ITEM = this.WEB_SERVICE_ENDPOINT + '/deleteItem?username=';
  public ALL_ORDERS = this.WEB_SERVICE_ENDPOINT + '/allOrders?username=';
  public ADD_ORDER = this.WEB_SERVICE_ENDPOINT + '/addOrder?username=';
  public ADD_BOOK = this.WEB_SERVICE_ENDPOINT + '/addBook';
  public DELETE_BOOK = this.WEB_SERVICE_ENDPOINT + '/deleteBook?ISBN=';
  public UPDATE_BOOK = this.WEB_SERVICE_ENDPOINT + '/updateBook?ISBN=';
  public GENERATE_REPORT = this.WEB_SERVICE_ENDPOINT + '/generateReport';
  public SEARCH_TITLE = this.WEB_SERVICE_ENDPOINT + '/searchTitle?title=';
  public SEARCH_AUTHOR = this.WEB_SERVICE_ENDPOINT + '/searchAuthor?author=';
  public SEARCH_GENRE = this.WEB_SERVICE_ENDPOINT + '/searchGenre?genre=';
  public ALL_BOOKS = this.WEB_SERVICE_ENDPOINT + '/allBooks';
  public GET_BOOK = this.WEB_SERVICE_ENDPOINT + '/getBook?ISBN=';
  public GET_CART = this.WEB_SERVICE_ENDPOINT + '/getCart?username=';
}
