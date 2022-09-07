//
//  FindMeeting.swift
//  iosApp
//
//  Created by Ivan Murashov on 07.09.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FindMeeting: View {
    
    @EnvironmentObject private var timezoneItems: TimezoneItems
    
    @State private var meetingHours: [Int] = []
    @State private var showHoursDialog = false
    @State private var startDate = Calendar.current.date(bySettingHour: 8, minute: 0, second: 0, of: Date())!
    @State private var endDate = Calendar.current.date(bySettingHour: 17, minute: 0, second: 0, of: Date())!
    
    private var timezoneHelper = TimeZoneHelperImpl()
    
    var body: some View {
        NavigationView {
            VStack {
                Spacer()
                    .frame(height: 8)
                Form {
                  Section(header: Text("Time Range")) {
                      // 1
                      DatePicker("Start Time", selection: $startDate, displayedComponents: .hourAndMinute)
                      // 2
                      DatePicker("End Time", selection: $endDate, displayedComponents: .hourAndMinute)
                  }
                  Section(header: Text("Time Zones")) {
                    // 3
                    ForEach(Array(timezoneItems.selectedTimezones), id: \.self) {  timezone in
                      HStack {
                        Text(timezone)
                        Spacer()
                      }
                    }
                  }
                } // Form
                Spacer()
                Button(action: {
                  // 1
                  meetingHours.removeAll()
                  // 2
                  let startHour = Calendar.current.component(.hour, from: startDate)
                  let endHour = Calendar.current.component(.hour, from: endDate)
                  // 3
                  let hours = timezoneHelper.search(
                    startHour: Int32(startHour),
                    endHour: Int32(endHour),
                    timeZoneStrings: Array(timezoneItems.selectedTimezones))
                  // 4
                  let hourInts = hours.map { kotinHour in
                    Int(truncating: kotinHour)
                  }
                  meetingHours += hourInts
                  // 5
                  showHoursDialog = true
                }, label: {
                  Text("Search")
                    .foregroundColor(Color.black)
                })
                Spacer()
                  .frame(height: 8)
            } // VStack
            .navigationTitle("Find Meeting Time")
            .sheet(isPresented: $showHoursDialog) {
              HourSheet(hours: $meetingHours)
            }
        }
    }
}

struct FindMeeting_Previews: PreviewProvider {
    static var previews: some View {
        FindMeeting()
    }
}
