const Reducer = (cart = [], action) => {
    switch (action.type) {
        case 'ADD':
            let addToCart=cart.filter((element)=>(element.productId===action.payload.productId))
        if(addToCart<1){
            return [...cart,action.payload]
        }else{
            return cart;
            }

        case 'REMOVE':
            return cart.filter((element) => element.productId !== action.payload.productId)

        case 'EMPTY':
            return cart = []

        case 'INCREASE':
            let increaseCart = cart.map(element => {
                if (element.productId === action.payload.productId) {
                    return { ...element, productAvailableQTY: element.productAvailableQTY + 1 }
                }
                return element
            })
            return increaseCart

        case 'DECREASE':
            let decreaseCart = cart.map(element => {
                if (element.productId === action.payload.productId) {
                    return { ...element, productAvailableQTY: element.productAvailableQTY - 1 }
                }
                return element
            })
            return decreaseCart

        default:
            return cart;
    }
}

export default Reducer;
