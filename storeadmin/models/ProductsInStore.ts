interface ProductInStore {
    id: number;
	productId: number;
	name: string;
	description: string;
	quantity: number;
	price: number;
}

/*
  {
	"id": 4,
	"version": 0,
	"createdDate": "2023-07-18T17:53:44.000+00:00",
	"lastModifiedDate": "2023-07-18T17:53:44.000+00:00",
	"productId": 4,
	"price": 400.00,
	"new": false

	private String      name;
	private String      description;

	private Integer     quantity = 0;

  },
*/

export default ProductInStore;