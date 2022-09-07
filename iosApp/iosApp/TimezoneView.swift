//
//  TimezoneView.swift
//  iosApp
//
//  Created by Ivan Murashov on 07.09.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TimezoneView: View {
    
    @EnvironmentObject private var timezoneItems: TimezoneItems
    
    @State private var currentDate = Date()
    
    @State private var showTimezoneDialog = false
    
    var timezoneHelper = TimeZoneHelperImpl()
    
    let timer = Timer.publish(every: 1000, on: .main, in: .common).autoconnect()
    
    var body: some View {
        NavigationView {
            VStack {
                TimeCard(timezone: timezoneHelper.currentTimeZone(),
                         time: DateFormatter.short.string(from: currentDate),
                         date: DateFormatter.long.string(from: currentDate))
                Spacer()
                List {
                    ForEach(Array(timezoneItems.selectedTimezones), id: \.self) { timezone in
                        NumberTimeCard(timezone: timezone,
                                       time: timezoneHelper.getTime(timeZoneID: timezone),
                                       hours: "\(timezoneHelper.hoursFromTimeZone(otherTimeZoneId: timezone)) hours from local",
                                       date: timezoneHelper.getDate(timeZoneID: timezone))
                        .withListModifier()
                    } // ForEach
                    .onDelete(perform: deleteItems)
                } // List
                .listStyle(.plain)
                Spacer()
            }
            .onReceive(timer) { input in
                currentDate = input
            }
            .navigationTitle("World Clocks")
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button(action: {
                        showTimezoneDialog = true
                    }) {
                        Image(systemName: "plus")
                            .frame(alignment: .trailing)
                            .foregroundColor(.black)
                    }
                }
            }
        }.fullScreenCover(isPresented: $showTimezoneDialog) {
            TimezoneDialog()
                .environmentObject(timezoneItems)
        }
    }
    
    func deleteItems(at offsets: IndexSet) {
        let timezoneArray = Array(timezoneItems.selectedTimezones)
        for index in offsets {
          let element = timezoneArray[index]
          timezoneItems.selectedTimezones.remove(element)
        }
    }
}

struct TimezoneView_Previews: PreviewProvider {
    static var previews: some View {
        TimezoneView()
    }
}
