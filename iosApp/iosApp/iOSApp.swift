import SwiftUI

@main
struct iOSApp: App {
    init() {
        KoinIosKt.doInitKoinIOS()

    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}