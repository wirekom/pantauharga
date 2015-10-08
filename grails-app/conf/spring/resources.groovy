// Place your Spring DSL code here

import com.pantau.marshallers.AuthUserMarshaller
import com.pantau.marshallers.ComodityInputMarshaller
import com.pantau.marshallers.ComodityMarshaller
import com.pantau.marshallers.ComodityTypeMarshaller
import com.pantau.user.UserDetailsService
import com.pantau.util.CustomObjectMarshallers

beans = {
    userDetailsService(UserDetailsService)
    customObjectMarshallers(CustomObjectMarshallers) {
        marshallers = [
                new ComodityMarshaller(),
                new ComodityTypeMarshaller(),
                new ComodityInputMarshaller(),
                new AuthUserMarshaller()
        ]
    }
}