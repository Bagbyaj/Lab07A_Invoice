class LineItem {
    private int quantity;
    private Product product;

    public LineItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public double calculateTotal() {
        return quantity * product.getUnitPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
}
