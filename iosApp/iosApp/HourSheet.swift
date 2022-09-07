//
//  HourSheet.swift
//  iosApp
//
//  Created by Ivan Murashov on 07.09.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct HourSheet: View {
    
    @Binding var hours: [Int]
    
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        NavigationView {
            VStack {
                List {
                    ForEach(hours, id: \.self) {  hour in
                        Text("\(hour)")
                    }
                } // List
            } // VStack
            .navigationTitle("Found Meeting Hours")
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button(action: {
                        presentationMode.wrappedValue.dismiss()
                    }) {
                        Text("Dismiss")
                            .frame(alignment: .trailing)
                            .foregroundColor(.black)
                    }
                }
            }
        }
    }
}

struct HourSheet_Previews: PreviewProvider {
    static var previews: some View {
        HourSheet(hours: .constant([8, 9, 10]))
    }
}
